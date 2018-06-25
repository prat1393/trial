package com.itko.lisa.matches;

import java.io.Serializable;

public class TxMappingPojo implements Serializable {

	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	public String getSourceVsiDate() {
		return sourceVsiDate;
	}
	public void setSourceVsiDate(String sourceVsiDate) {
		this.sourceVsiDate = sourceVsiDate;
	}
	public String getSourceLisaTxId() {
		return sourceLisaTxId;
	}
	public void setSourceLisaTxId(String sourceLisaTxId) {
		this.sourceLisaTxId = sourceLisaTxId;
	}
	public String getMergeLisaTxId() {
		return mergeLisaTxId;
	}
	public void setMergeLisaTxId(String mergeLisaTxId) {
		this.mergeLisaTxId = mergeLisaTxId;
	}
	
	
	public String getBackendName() {
		return backendName;
	}
	public void setBackendName(String backendName) {
		this.backendName = backendName;
	}


	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public int getNumberOfArguments() {
		return numberOfArguments;
	}
	public void setNumberOfArguments(int numberOfArguments) {
		this.numberOfArguments = numberOfArguments;
	}


	public String isAllRequestParamtersAnything() {
		return allRequestParamtersAnything;
	}
	public void setAllRequestParamtersAnything(String allRequestParamtersAnything) {
		this.allRequestParamtersAnything = allRequestParamtersAnything;
	}


	public String scriptName;
	public String sourceVsiDate;
	public String sourceLisaTxId;
	public String mergeLisaTxId;
	public String backendName;
	public String operationName;
	public int numberOfArguments;
	public String allRequestParamtersAnything;

}
