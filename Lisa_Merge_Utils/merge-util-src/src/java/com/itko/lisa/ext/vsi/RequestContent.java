
package com.itko.lisa.ext.vsi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itko.lisa.ext.enumeration.Operation;

/**
 * Bean object that holds the details for editing request content of VSI
 *
 * @author Ashutosh Satyam (ashutosh.satyam@ca.com)
 * @version 1.0
 */
public class RequestContent {
	
	/**
	 * Request Argument Name as specified in service image
	 */
	private String argname;	
	
	/**
	 * Default Request Argument Value. 
	 * This value should be used for a transaction where it doesn't map to 
	 * contextual information. Only in cases where this value is not specified 
	 * set comparison operator to Anything. For all other cases it should 
	 * always be set to EQUALS
	 * @see com.itko.lisa.vse.stateful.model.ArgumentType.Operator
	 */
	private String argvalue;	 
	
	/**
	 * Indicates if response node has to be added, updated or removed.
	 * For request the only two possible values will be either INSERT or DELETE
	 * @see com.itko.lisa.ext.enumeration.Operation
	 */
	private Operation opr;
	
	/**
	 * List of all the contextual keys. 
	 * Key is typically the request argument names to uniquely identify 
	 * a transaction. It has been provided for convenience to check if 
	 * transaction meets the contextual criteria.
	 * 
	 * This list can be empty if there is no contextual mapping information
	 * 
	 */
	private List<String> ctxtKeys = new ArrayList<String>();
	
	/**
	 * Map with contextual entries where key holds all the contextual detail  
	 * and value holds the argument value. When evaluating a transaction use the
	 * <code>ctxtKeys</code> to construct key structure as explained below.
	 * If the constructed key exist in this map then use the value against it
	 * as the request argument value.
	 * 
	 * Key is concatenated string with first literal being argument name 
	 * followed by argument value which are separated by ":".If there are more
	 * than one argument that is separated by "::".
	 * 
	 * Example:
	 * [arg0_STRING:cingMobileIMEI::arg1_INT:2, DLS]
	 *  --------------------------------------  ---
	 *            KEY						   VALUE
	 *            
	 * Note: This map can be empty if there is no contextual information
	 */
	private Map<String,String> ctxtMap = new HashMap<String,String>();
	
	/**
	 * @return the argname
	 */
	public String getArgname() {
		return argname;
	}
	/**
	 * @param argname the argname to set
	 */
	public void setArgname(String argname) {
		this.argname = argname;
	}
	/**
	 * @return the argvalue
	 */
	public String getArgvalue() {
		return argvalue;
	}
	/**
	 * @param argvalue the argvalue to set
	 */
	public void setArgvalue(String argvalue) {
		this.argvalue = argvalue;
	}
	
	/**
	 * @return the opr
	 */
	public Operation getOpr() {
		return opr;
	}
	
	/**
	 * @param opr the opr to set
	 */
	public void setOpr(Operation opr) {
		this.opr = opr;
	}
	
	/**
	 * @return the ctxtKeys
	 */
	public List<String> getCtxtKeys() {
		return ctxtKeys;
	}
	/**
	 * @param ctxtKeys the ctxtKeys to set
	 */
	public void setCtxtKeys(List<String> ctxtKeys) {
		this.ctxtKeys = ctxtKeys;
	}
	/**
	 * @return the ctxtMap
	 */
	public Map<String, String> getCtxtMap() {
		return ctxtMap;
	}
	/**
	 * @param ctxtMap the ctxtMap to set
	 */
	public void setCtxtMap(Map<String, String> ctxtMap) {
		this.ctxtMap = ctxtMap;
	}
	
	/**
	 * Default constructor
	 */
	public RequestContent() {
		
	}
	
	/**
	 * @param argname
	 * @param argvalue
	 * @param opr
	 * @param ctxtKeys
	 * @param ctxtMap
	 */
	public RequestContent(String argname, String argvalue, Operation opr,
			List<String> ctxtKeys, Map<String, String> ctxtMap) {
		super();
		this.argname = argname;
		this.argvalue = argvalue;
		this.opr = opr;
		this.ctxtKeys = ctxtKeys;
		this.ctxtMap = ctxtMap;
	}
	
	@Override
	public String toString() {
		return "RequestContent [Arg Name = " + getArgname()
				+ ", Arg Value = " + getArgvalue() + ", Operation Type = " 
				+ getOpr() + ", CtxtKeys = " + getCtxtKeys() + ", CtxtMap = "
				+ getCtxtMap() + "]";
	}
	
}
