
package com.itko.lisa.ext.vsi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itko.lisa.ext.enumeration.Operation;

/**
 * Bean object that holds the details for editing response content of VSI
 *
 * @author Ashutosh Satyam (ashutosh.satyam@ca.com)
 * @version 1.0
 */
public class ResponseContent {
	
	/**
	 * Default XPath to identify node within Response XML. 
	 * It should be applied for a transaction where contextual mapping is
	 * irrelevant.
	 */
	private String xpath;	
	
	/**
	 * Default node value to be used. 
	 * This value should be used for a transaction where it doesn't map to 
	 * contextual information. 
	 */
	private String xpathvalue;	 
	
	/**
	 * Indicates if response node has to be added, updated or removed
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
	 * [arg0_STRING:cingMobileIMEI::arg1_INT:2, 
	 *  --------------------------------------  
	 *            KEY						   
	 *  //billingAccountNumber:<string>Wireline</string>]          
	 *  -----------------------------------------------          
	 *     VALUE ( xpath : node value )       
	 * 
	 * Here VALUE contains xpath and node value. This flexibility enables
	 * to handle case where contextual transaction needs to be edited in a 
	 * different way. Also if its a delete operation then only xpath would be
	 * supplied as value.
	 * 
	 * Note: This map can be empty if there is no contextual information
	 */
	private Map<String,String> ctxtMap = new HashMap<String,String>();
	
	
	/**
	 * @return the xpath
	 */
	public String getXpath() {
		return xpath;
	}
	/**
	 * @param xpath the xpath to set
	 */
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	/**
	 * @return the xpathvalue
	 */
	public String getXpathvalue() {
		return xpathvalue;
	}
	/**
	 * @param xpathvalue the xpathvalue to set
	 */
	public void setXpathvalue(String xpathvalue) {
		this.xpathvalue = xpathvalue;
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
	public ResponseContent() {
		
	}
	
	/**
	 * @param xpath
	 * @param xpathvalue
	 * @param opr
	 * @param ctxtKeys
	 * @param ctxtMap
	 */
	public ResponseContent(String xpath, String xpathvalue, Operation opr,
			List<String> ctxtKeys, Map<String, String> ctxtMap) {
		super();
		this.xpath = xpath;
		this.xpathvalue = xpathvalue;
		this.opr = opr;
		this.ctxtKeys = ctxtKeys;
		this.ctxtMap = ctxtMap;
	}
	
	@Override
	public String toString() {
		return "ResponseContent [Xpath = " + getXpath()
				+ ", Node Value = " + getXpathvalue() + ", Operation Type = "
				+ getOpr() + ", CtxtKeys = " + getCtxtKeys()
				+ ", CtxtMap = " + getCtxtMap() + "]";
	}

}
