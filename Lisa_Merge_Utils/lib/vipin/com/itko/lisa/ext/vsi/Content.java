
package com.itko.lisa.ext.vsi;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean object that holds the following information read from spreadsheet
 *  - Operation Name
 *  - No. of Arguments
 *  - List<RequestContent>
 *  - List<ResponseContent>
 *
 * @author Ashutosh Satyam (ashutosh.satyam@ca.com)
 * @version 1.0
 */
public class Content {
	
	/**
	 * Operation Name as specified in service image
	 */
	private String oprname;	
	
	/**
	 * Number of request arguments
	 */
	private int noofargs;	
	
	/**
	 * List of <code>RequestContent</code>
	 */
	private List<RequestContent> reqContent;
	
	/**
	 * List of <code>ResponseContent</code>
	 */
	private List<ResponseContent> rspContent;

	/**
	 * @return the oprname
	 */
	public String getOprname() {
		return oprname;
	}

	/**
	 * @param oprname the oprname to set
	 */
	public void setOprname(String oprname) {
		this.oprname = oprname;
	}

	/**
	 * @return the noofargs
	 */
	public int getNoofargs() {
		return noofargs;
	}

	/**
	 * @param noofargs the noofargs to set
	 */
	public void setNoofargs(int noofargs) {
		this.noofargs = noofargs;
	}

	/**
	 * @return the reqContent
	 */
	public List<RequestContent> getReqContent() {
		return reqContent;
	}

	/**
	 * @param reqContent the reqContent to set
	 */
	public void setReqContent(List<RequestContent> reqContent) {
		this.reqContent = reqContent;
	}
	
	/**
	 * @param e add a RequestContent to list
	 */
	public void addReqContent(RequestContent e) {
		if (this.reqContent == null) {
			this.reqContent = new ArrayList<RequestContent>();
		}

		if (!this.reqContent.contains(e)) {
			this.reqContent.add(e);
		}
	}

	/**
	 * @return the rspContent
	 */
	public List<ResponseContent> getRspContent() {
		return rspContent;
	}

	/**
	 * @param rspContent the rspContent to set
	 */
	public void setRspContent(List<ResponseContent> rspContent) {
		this.rspContent = rspContent;
	}
	
	/**
	 * @param e add a ResponseContent to list
	 */
	public void addRspContent(ResponseContent e) {
		if (this.rspContent == null) {
			this.rspContent = new ArrayList<ResponseContent>();
		}

		if (!this.rspContent.contains(e)) {
			this.rspContent.add(e);
		}
	}
	
	/**
	 * Default constructor
	 */
	public Content() {
		
	}
	
	/**
	 * @param oprname
	 * @param noofargs
	 * @param reqContent
	 * @param rspContent
	 */
	public Content(String oprname, int noofargs,
			List<RequestContent> reqContent, List<ResponseContent> rspContent) {
		super();
		this.oprname = oprname;
		this.noofargs = noofargs;
		this.reqContent = reqContent;
		this.rspContent = rspContent;
	}

	@Override
	public String toString() {
		return "Content [Operation Name = " + getOprname() + ", No. of Args = "
				+ getNoofargs() + ", Request Content = " + getReqContent()
				+ ", Response Content = " + getRspContent() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noofargs;
		result = prime * result + ((oprname == null) ? 0 : oprname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (noofargs != other.noofargs)
			return false;
		if (oprname == null) {
			if (other.oprname != null)
				return false;
		} else if (!oprname.equals(other.oprname))
			return false;
		return true;
	}
}
