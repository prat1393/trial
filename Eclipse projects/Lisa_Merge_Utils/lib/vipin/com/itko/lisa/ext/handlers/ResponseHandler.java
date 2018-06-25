
package com.itko.lisa.ext.handlers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itko.lisa.ext.enumeration.Operation;
import com.itko.lisa.ext.util.ResponseParser;
import com.itko.lisa.ext.util.SiTransactionValidator;
import com.itko.lisa.ext.vsi.ResponseContent;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.Response;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransactionNode;
import com.itko.util.ParameterList;

/**
 * Utility class to edit the Response content of VSI.
 * It receives the <code>Transaction</code> and the changes to be applied in 
 * Response supplied via <code>ResponseContent</code>.
 * 
 * Supports both addition or removal of response element.
 * 
 * @see com.itko.lisa.vse.stateful.model.Transaction
 * @see com.itko.lisa.vse.stateful.model.Response
 * @see com.itko.lisa.ext.vsi.ResponseContent
 * 
 * @version 1.0
 */
public class ResponseHandler {

	
	/**
	 * 
	 * 
	 * 
	 * @param respFilters
	 * @param txn
	 * @throws Exception
	 */
	public static void handleMetaResponse(List<ResponseContent> respFilters,TransactionNode txnNode) throws Exception{
		List<Response> responseList = txnNode.getResponses();
		if(null != responseList && !responseList.isEmpty()){
			for(Response responseObj : responseList){
				if (responseObj.isBodyAvailable()) {
					String strResponse = responseObj.getBodyAsString();
					if (null != respFilters && (!respFilters.isEmpty())) {
						for (ResponseContent respFilter : respFilters) {
							Request req = txnNode.getRequest();
							if (null != respFilter.getOpr() && Operation.DELETE.equals(respFilter.getOpr())) {
								strResponse = removeAttrFromResponseWrapper(respFilter,req,strResponse);
							}if (null != respFilter.getOpr() && Operation.INSERT.equals(respFilter.getOpr())) {
								strResponse = addAttrFromResponseWrapper(respFilter,req,strResponse);
							} else if (null != respFilter.getOpr() && Operation.UPDATE.equals(respFilter.getOpr())) {
								strResponse = updateAttrFromResponseWrapper(respFilter, req, strResponse);	
							}
							responseObj.setBody(strResponse);
						}
					}
				}else{
					//Looks like Empty Body.
					continue;
				}
			}
		}else{
			System.out.println("ResponseContent cannot be Empty.");
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param respFilters
	 * @param txn
	 * @throws Exception
	 */
	public static void handleResponse(List<ResponseContent> respFilters,Transaction txn) throws Exception{
		List<Response> responseList = txn.getResponses();
		if(null != responseList && !responseList.isEmpty()){
			for(Response responseObj : responseList){
				if (responseObj.isBodyAvailable()) {
					String strResponse = responseObj.getBodyAsString();
					if (null != respFilters && (!respFilters.isEmpty())) {
						for (ResponseContent respFilter : respFilters) {
							Request req = txn.getRequest();
							if (null != respFilter.getOpr() && Operation.DELETE.equals(respFilter.getOpr())) {
								strResponse = removeAttrFromResponseWrapper(respFilter,req,strResponse);
							}if (null != respFilter.getOpr() && Operation.INSERT.equals(respFilter.getOpr())) {
								strResponse = addAttrFromResponseWrapper(respFilter,req,strResponse);
							} else if (null != respFilter.getOpr() && Operation.UPDATE.equals(respFilter.getOpr())) {
								strResponse = updateAttrFromResponseWrapper(respFilter, req, strResponse);	
							}
							responseObj.setBody(strResponse);
						}
					}
				}else{
					//Looks like Empty Body.
					continue;
				}
			}
		}else{
			System.out.println("ResponseContent cannot be Empty.");
		}
	}
	
	/**
	 * 
	 * @param respFilter
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private static String removeAttrFromResponseWrapper(ResponseContent respFilter,Request request,String response) throws Exception{
		if(null != respFilter.getCtxtMap() && !respFilter.getCtxtMap().isEmpty()){
			response = validateTxnAndDeleteAttribute(request, response, respFilter);
		}
		if(null != respFilter.getXpath()){
			response = ResponseParser.removeAttrFromResponse(response,respFilter.getXpath());
		}
		return response;
	}
	
	/**
	 * 
	 * @param respFilter
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private static String addAttrFromResponseWrapper(ResponseContent respFilter,Request request,String response) throws Exception{
		if (null != respFilter.getCtxtMap() && !respFilter.getCtxtMap().isEmpty()) {
			response = validateTxnAndAddAttribute(request, response, respFilter);
		} 
		if (null != respFilter.getXpath()) {
			response = ResponseParser.addAttrToResponse(response, respFilter.getXpath(),respFilter.getXpathvalue());
		} 
		return response;
	}	
	
	private static String updateAttrFromResponseWrapper(ResponseContent respFilter,Request request,String response) throws Exception{
		if (null != respFilter.getCtxtMap() && !respFilter.getCtxtMap().isEmpty()) {
			response = validateTxnAndUpdateAttribute(request, response, respFilter);
		}
		if (null != respFilter.getXpath()) {
			response = ResponseParser.updateAttrToResponse(response, respFilter.getXpath(),respFilter.getXpathvalue());
		}
		return response;
	}	
	
	
	/**
	 * here we are validating if transaction is valid as per our Contextual Criteria
	 * Method will return updated response with deleted attribute if transaction Request satisfy all the Contextual criteria. 
	 * 
	 * "Here we are assuming that we have contextual map for given operation."
	 * 
	 * 
	 * @param req
	 * @param respFilter
	 * @return
	 */
	private static String validateTxnAndDeleteAttribute(Request request,String response,ResponseContent respFilter) throws Exception{
		ParameterList pList = request.getArguments();			
		if (SiTransactionValidator.isRequestHasAllCtxtKey(pList, respFilter.getCtxtKeys())) {
			Map<String, String> ctxtMap = respFilter.getCtxtMap();
			Set<String> keySet = ctxtMap.keySet();
			//arg0_STRING:cingMobileIMEI::arg1_INT:2
			for (String key : keySet) {
				String[] qFilterCriteria = null;
				if (key.contains("::")) {
					qFilterCriteria = key.split("::");
				} else {
					qFilterCriteria = new String[] { key };
				}
				if(SiTransactionValidator.isContexualCriteriaMatch(request, qFilterCriteria)){
					String xPath = ctxtMap.get(key);					
					response = ResponseParser.removeAttrFromResponse(response,xPath);
				}
			}
		}
		return response;		
	}	
	
	/**
	 * here we are validating if transaction is valid as per our Contextual Criteria
	 * Method will return updated response with new values if transaction Request satisfy all the Contextual criteria.
	 * "Here we are assuming that we have contextual map for given operation."
	 * 
	 * @param req
	 * @param respFilter
	 * @return
	 */
	private static String validateTxnAndAddAttribute(Request request,String response,ResponseContent respFilter) throws Exception{
		ParameterList pList = request.getArguments();			
		if (SiTransactionValidator.isRequestHasAllCtxtKey(pList, respFilter.getCtxtKeys())) {
			Map<String, String> ctxtMap = respFilter.getCtxtMap();
			Set<String> keySet = ctxtMap.keySet();
			//arg0_STRING:cingMobileIMEI::arg1_INT:2
			for (String key : keySet) {
				String[] qFilterCriteria = null;
				if (key.contains("::")) {
					qFilterCriteria = key.split("::");
				} else {
					qFilterCriteria = new String[] { key };
				}
				if(SiTransactionValidator.isContexualCriteriaMatch(request, qFilterCriteria)){
					String xPathDetail = ctxtMap.get(key);
					if(xPathDetail.split(":").length>=2){
						String [] xPathAndValue = xPathDetail.split(":");
						response = ResponseParser.addAttrToResponse(response, xPathAndValue[0], xPathAndValue[1]);
					}
				}
			}
		}
		return response;		
	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param respFilter
	 * @return
	 * @throws Exception
	 */
	private static String validateTxnAndUpdateAttribute(Request request,String response,ResponseContent respFilter)throws Exception{
		ParameterList pList = request.getArguments();			
		if (SiTransactionValidator.isRequestHasAllCtxtKey(pList, respFilter.getCtxtKeys())) {
			Map<String, String> ctxtMap = respFilter.getCtxtMap();
			Set<String> keySet = ctxtMap.keySet();
			//arg0_STRING:cingMobileIMEI::arg1_INT:2
			for (String key : keySet) {
				String[] qFilterCriteria = null;
				if (key.contains("::")) {
					qFilterCriteria = key.split("::");
				} else {
					qFilterCriteria = new String[] { key };
				}
				if(SiTransactionValidator.isContexualCriteriaMatch(request, qFilterCriteria)){
					String xPathDetail = ctxtMap.get(key);
					if(xPathDetail.split(":").length>=2){
						String [] xPathAndValue = xPathDetail.split(":");
						response = ResponseParser.updateAttrToResponse(response, xPathAndValue[0], xPathAndValue[1]);
					}
				}
			}
		}
		return response;
	}
	
}