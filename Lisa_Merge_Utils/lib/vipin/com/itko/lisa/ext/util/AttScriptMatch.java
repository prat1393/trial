package com.itko.lisa.ext.util;

import java.util.ArrayList;
import java.util.List;

import com.itko.lisa.vse.stateful.model.Transaction;

public class AttScriptMatch {

	private String serviceImageName = null;
	private int lisaTransactionId = 0;
	private int lisaRequestId = 0;
	private String matchStyle = null;
	private String comment = "";
	private String status = "INIT";
	private String time;
	private List<Transaction> transList = new ArrayList<Transaction>();

	public AttScriptMatch(String serviceImageName, int transId, int reqId,
			String matchStyle, String comemntStr,String time) {
		super();
		this.lisaRequestId = reqId;
		this.lisaTransactionId = transId;
		this.matchStyle = matchStyle;
		this.comment = this.comment + comemntStr;
		this.serviceImageName = serviceImageName;
		this.time = time;

	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void addComment(String comment) {
		this.comment = this.comment + comment;
	}

	/**
	 * @return the serviceImageName
	 */
	public String getServiceImageName() {
		return serviceImageName;
	}

	/**
	 * @param serviceImageName
	 *            the serviceImageName to set
	 */
	public void setServiceImageName(String serviceImageName) {
		this.serviceImageName = serviceImageName;
	}

	/**
	 * @return the lisaTransactionId
	 */
	public int getLisaTransactionId() {
		return lisaTransactionId;
	}

	/**
	 * @param lisaTransactionId
	 *            the lisaTransactionId to set
	 */
	public void setLisaTransactionId(int lisaTransactionId) {
		this.lisaTransactionId = lisaTransactionId;
	}

	/**
	 * @return the lisaRequestId
	 */
	public int getLisaRequestId() {
		return lisaRequestId;
	}

	/**
	 * @param lisaRequestId
	 *            the lisaRequestId to set
	 */
	public void setLisaRequestId(int lisaRequestId) {
		this.lisaRequestId = lisaRequestId;
	}

	/**
	 * @return the matchStyle
	 */
	public String getMatchStyle() {
		return matchStyle;
	}

	/**
	 * @param matchStyle
	 *            the matchStyle to set
	 */
	public void setMatchStyle(String matchStyle) {
		this.matchStyle = matchStyle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " [serviceImageName=" + serviceImageName
				+ ", lisaTransactionId=" + lisaTransactionId
				+ ", lisaRequestId=" + lisaRequestId + ", matchStyle="
				+ matchStyle + ", comment=" + comment + ", transList="
				+ transList + "]";
	}

	/**
	 * @return the transList
	 */
	public List<Transaction> getTransList() {
		return transList;
	}

	/**
	 * @param transList
	 *            the transList to set
	 */
	public void setTransList(List<Transaction> transList) {
		this.transList = transList;
	}

	public void addTrans(Transaction trans) {
		if (!this.transList.isEmpty()) {
			this.transList.add(trans);
		} else
			this.transList.add(trans);

	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
