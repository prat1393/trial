package com.att.login.model;

import lombok.Data;

@Data
public class CSPAuthData {

	private String hrid;

	private String hrCookieSig;

	private String empType;

	private String authStr;

	private String privs;

	private String attuid;

	private String authEnv;

	private String timestamp;

	private String hrFirstName;

	private String hrLastName;

	private String hrEmail;

	private String hrWorkPhone;

	private String hrMiddleName;

	private String hrManagerAttuid;

	private String hrNameSuffix;

	private String hrManagerHrid;

	private String hrPatternA;

	private String hrClli;

	private String hrFmlOrgCode;

	private String hrSalaryGrade;

	private String accessPrivilege;

}
