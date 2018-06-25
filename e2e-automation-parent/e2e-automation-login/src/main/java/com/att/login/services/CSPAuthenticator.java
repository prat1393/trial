package com.att.login.services;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.att.login.model.CSPAuthData;

//import com.att.odt.common.//LogUtils;

import esGateKeeper.esGateKeeper;
import esGateKeeper.hrCookie;

public class CSPAuthenticator {
	private static final int offsetFromGMT = (Calendar.getInstance().get(15) + Calendar.getInstance().get(16))
			/ 3600000;
	private static String deploymentSetting = "PROD";

	final static String CLASSNAME = CSPAuthenticator.class.getCanonicalName();

	public CSPAuthData validate(HttpServletRequest req) throws Exception {
		// LogUtils.infoLog(CLASSNAME, "validate()", "Entering");
		String attESSec = null;
		String attESHr = null;
		Cookie[] cookies = req.getCookies();

		// LogUtils.debugLog(CLASSNAME, "validate()", "cookies:" + cookies);
		if (cookies != null) {
			for (int eSSec = 0; eSSec < cookies.length; ++eSSec) {
				Cookie tokens = cookies[eSSec];
				/*
				 * //LogUtils.debugLog(CLASSNAME, "validate()", "cookies:" +
				 * tokens);
				 * 
				 * //LogUtils.debugLog(CLASSNAME, "validate()",
				 * "cookieName:>>>>>>>>>>>>>>>>>>>>>>>" + tokens.getName());
				 * //LogUtils.debugLog(CLASSNAME, "validate()",
				 * "in if cooke length:>>>>>>>>>>>>>>>>" +
				 * tokens.getValue().length()); //LogUtils.debugLog(CLASSNAME,
				 * "validate()", "Value of Cooke: >>>>>>>>>>>>>>>" +
				 * tokens.getValue() + "<<<<<<<<<<<<<<<<<");
				 */
				if (tokens.getName() != null && "attESSec".equals(tokens.getName()) && isNoneEmpty(tokens.getValue())) {
					// LogUtils.debugLog(CLASSNAME, "validate()", "in if
					// cooke:>>>>>>>>>>>>>>>");
					attESSec = esGateKeeper.esGateKeeper(tokens.getValue(), "CSP", deploymentSetting, offsetFromGMT);
					// LogUtils.debugLog(CLASSNAME, "validate()", "attESSec
					// value:" + attESSec);
					if (attESSec == null || attESSec.length() == 0) {
						// LogUtils.infoLog(CLASSNAME, "validate()", "no cookie
						// or bad cookie");
						attESSec = null;
						break;
					}
				} else if (tokens.getName().equals("attESHr") && tokens.getValue().length() > 0) {
					// LogUtils.infoLog(CLASSNAME, "validate()", "in else if is
					// attESHr:");
					attESHr = tokens.getValue();
					// LogUtils.debugLog(CLASSNAME, "validate()", "attESHr
					// value:" + attESHr);
				}
			}
		}

		if (attESSec != null && attESHr != null) {
			String var13;
			try {
				var13 = URLDecoder.decode(attESSec, "utf-8");
				// LogUtils.debugLog(CLASSNAME, "validate()", "eSSec :: " +
				// var13);
			} catch (UnsupportedEncodingException var12) {
				throw var12;
			}

			String[] var14 = var13.split("\\|", -1);
			if (var14.length != 8) {
				throw new Exception("AT&T Global Login technical error: broken cookie \"attESSec\": token count ("
						+ var14.length + ") not equal to " + 8);
			} else {
				CSPAuthData var15 = new CSPAuthData();
				var15.setHrid(var14[0]);
				var15.setHrCookieSig(var14[1]);
				var15.setEmpType(var14[2]);
				var15.setAuthStr(var14[3]);
				var15.setPrivs(var14[4]);
				var15.setAttuid(var14[5]);
				var15.setAuthEnv(var14[6]);
				var15.setTimestamp(var14[7]);
				String eSHr = null;

				try {
					eSHr = URLDecoder.decode(attESHr, "utf-8");
					// LogUtils.debugLog(CLASSNAME, "validate()", "eSHr :: " +
					// eSHr);
				} catch (UnsupportedEncodingException var11) {
					throw new Exception("URLDecoder error", var11);
				}

				hrCookie oCookieVerifier = new hrCookie();
				int hrCookieValid = oCookieVerifier.verify(eSHr, var15.getHrCookieSig());
				if (hrCookieValid != 0) {
					throw new Exception("Broken cookie \"attESHr\": signature check returned " + hrCookieValid);
				} else {
					var14 = eSHr.split("\\|", -1);
					if (var14.length != 12) {
						throw new Exception(
								"AT&T Global Login technical error: broken cookie \"attESHr\": token count ("
										+ var14.length + ") not equal to " + 12);
					} else {
						var15.setHrFirstName(var14[0]);
						var15.setHrLastName(var14[1]);
						var15.setHrEmail(var14[2]);
						var15.setHrWorkPhone(var14[3]);
						var15.setHrMiddleName(var14[4]);
						var15.setHrManagerAttuid(var14[5]);
						var15.setHrNameSuffix(var14[6]);
						var15.setHrManagerHrid(var14[7]);
						var15.setHrPatternA(var14[8]);
						var15.setHrClli(var14[9]);
						var15.setHrFmlOrgCode(var14[10]);
						var15.setHrSalaryGrade(var14[11]);
						// LogUtils.debugLog(CLASSNAME, "validate()", "returning
						// auth :: " + var15);
						return var15;
					}
				}
			}
		} else {

			CSPAuthData authData = new CSPAuthData();

			authData.setAttuid(req.getParameter("loginUserId"));

			// LogUtils.infoLog(CLASSNAME, "validate()", "Exiting from");
			return authData;

		}

	}

	public boolean isAnyEmpty(CharSequence... css) {
		if (isEmpty(css)) {
			return false;
		}
		for (CharSequence cs : css) {
			if (isEmpty(cs)) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty(Object[] array) {
		return getLength(array) == 0;
	}

	public boolean isEmpty(CharSequence cs) {
		return (cs == null) || (cs.length() == 0);
	}

	public boolean isNoneEmpty(CharSequence... css) {
		return !isAnyEmpty(css);
	}

	public int getLength(Object array) {
		if (array == null) {
			return 0;
		}
		return Array.getLength(array);
	}

}
