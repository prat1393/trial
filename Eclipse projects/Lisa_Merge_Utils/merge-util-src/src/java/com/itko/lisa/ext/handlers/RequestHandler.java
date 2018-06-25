
package com.itko.lisa.ext.handlers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itko.lisa.ext.enumeration.Operation;
import com.itko.lisa.ext.util.SiTransactionValidator;
import com.itko.lisa.ext.vsi.RequestContent;
import com.itko.lisa.vse.stateful.model.ArgumentType;
import com.itko.lisa.vse.stateful.model.ArgumentType.Operator;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransactionNode;
import com.itko.util.Parameter;
import com.itko.util.ParameterList;

/**
 * Utility class to edit the Request content of VSI.
 * It receives the <code>Transaction</code> and the changes to be done in 
 * Request supplied via <code>RequestContent</code>
 * 
 * Supports both addition or removal of request arguments.
 * 
 * @see com.itko.lisa.vse.stateful.model.Transaction
 * @see com.itko.lisa.vse.stateful.model.Request
 * @see com.itko.lisa.ext.vsi.RequestContent
 * 
 * @version 1.0
 */
public class RequestHandler {
	
	public static void handleMetaRequest(List<RequestContent> reqFilters,TransactionNode txnNode) throws Exception{
		Request req = txnNode.getRequest();
		if (null != req) {
			if (null != reqFilters && (!reqFilters.isEmpty())) {
				for (RequestContent reqFilter : reqFilters) {
					if (null != reqFilter.getOpr() && Operation.INSERT.equals(reqFilter.getOpr())) {
						addAttrToRequest(reqFilter, req);
					} else if (null != reqFilter.getOpr() && Operation.DELETE.equals(reqFilter.getOpr())) {
						removeAttrToRequest(reqFilter, req);
					} 
				}
			}else{
				System.out.println("RequestContent cannot be Empty.");
			}
		}
	}
	
	
	
	public static void handlleRequest(List<RequestContent> reqFilters,Transaction txn) throws Exception{
		Request req = txn.getRequest();
		if (null != req) {
			if (null != reqFilters && (!reqFilters.isEmpty())) {
				for (RequestContent reqFilter : reqFilters) {
					if (null != reqFilter.getOpr() && Operation.INSERT.equals(reqFilter.getOpr())) {
						addAttrToRequest(reqFilter, req);
					} else if (null != reqFilter.getOpr() && Operation.DELETE.equals(reqFilter.getOpr())) {
						removeAttrToRequest(reqFilter, req);
					} 
				}
			}else{
				System.out.println("RequestContent cannot be Empty.");
			}
		}
	}
	
	private static Request addAttrToRequest(RequestContent reqFilter,Request request) throws Exception{
		if (null != reqFilter.getCtxtMap() && !reqFilter.getCtxtMap().isEmpty()) {
			request = validateTxnAndAddContextualAttribute(reqFilter, request);			
		}		
		if (null != reqFilter.getArgname()) {
			if(!request.getArguments().containsKey(reqFilter.getArgname())){
				String keyValue = reqFilter.getArgname()+":"+reqFilter.getArgvalue();
				request = addStaticRequestAttribute(keyValue, request);
			}
		}
		return request;
	}
	
	/**
	 * 
	 * @param reqFilter
	 * @param request
	 * @return
	 * @throws Exception
	 */	
	private static Request removeAttrToRequest(RequestContent reqFilter,Request request) throws Exception{		
		if (null != reqFilter.getArgname()) {
			String argName = reqFilter.getArgname();
			return removeStaticRequestAttribute(argName, request);
		}
		return request;
	}
	
	/**
	 * here we are validating if transaction is valid as per our Contextual Criteria
	 * Method will return updated request with new values if transaction Request satisfy all the Contextual criteria.
	 * "Here we are assuming that we have contextual map for given operation."
	 * 
	 * @param req
	 * @param respFilter
	 * @return
	 */
	private static Request validateTxnAndAddContextualAttribute(RequestContent reqFilter,Request request) throws Exception{
		ParameterList pList = request.getArguments();			
		if (SiTransactionValidator.isRequestHasAllCtxtKey(pList, reqFilter.getCtxtKeys())) {
			Map<String, String> ctxtMap = reqFilter.getCtxtMap();
			Set<String> keySet = ctxtMap.keySet();
			for (String key : keySet) {
				String[] qFilterCriteria = null;
				if (key.contains("::")) {
					qFilterCriteria = key.split("::");
				} else {
					qFilterCriteria = new String[] { key };
				}
				if (SiTransactionValidator.isContexualCriteriaMatch(request,qFilterCriteria)) {
					String arg_value = ctxtMap.get(key);
					request = addStaticRequestAttribute(arg_value, request);
				}
			}
		}
		return request;		
	}		
	
	/**
	 * 
	 * @param keyValue
	 * @param request
	 * @return
	 */
	private static Request addStaticRequestAttribute(String keyValue,Request request){
		ParameterList paramList = request.getArguments();
		if(keyValue.contains(":") && keyValue.split(":").length>=2){
			String [] key_Value = keyValue.split(":");
			Parameter param = new Parameter(key_Value[0],key_Value[1]);
			ArgumentType argType = new ArgumentType();
			argType.setOperator(Operator.EQUALS);
			param.setType(argType);
			paramList.addParameter(param);		
			request.setArguments(paramList);
			request.ensureBodyIsPresent();
		}
		return request;
	}	
	
	/**
	 * 
	 * @param argName
	 * @param request
	 * @return
	 */
	private static Request removeStaticRequestAttribute(String argName,Request request){
		ParameterList paramList = request.getArguments();
		paramList.removeParameter(argName);
		request.ensureBodyIsPresent();
		return request;
	}
}
