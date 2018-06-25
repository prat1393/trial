package com.itko.lisa.ext.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itko.lisa.ext.vsi.ResponseContent;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.util.ParameterList;

public class SiTransactionValidator {
		
	/**
	 * Base on criteria we will validate the request.
	 * 
	 * @param req
	 * @param criteria  : Contain Request Key and its corresponding value separated by ':'
	 * @return
	 */
	
	public static boolean isContexualCriteriaMatch(Request req,String [] criteria){
		ParameterList reqParamList = req.getArguments();
		for(String str : criteria){
			if (null != str && str.split(":").length >= 2) {
				String key = str.split(":")[0];
				String value = str.split(":")[1];
				if (value.equals(reqParamList.get(key))) {
					// do nothing
				} else {
					return false;
				}
			}else{
				System.out.println("validateRequest():Key Value Criteria is missing");
			}
		}
		return true;
	}
	
	public static boolean isRequestHasAllCtxtKey(ParameterList reqParameterList,List<String> ctxKeys){
		if (null != ctxKeys) {
			for (String ctxkey : ctxKeys) {
				if(null != reqParameterList){
					if(!reqParameterList.containsKey(ctxkey)){
						return false;
					}
				}
			}
		}
		return true;
	}

}
