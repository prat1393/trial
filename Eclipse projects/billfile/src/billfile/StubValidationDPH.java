package com.itko.lisa.ext.vse;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.itko.lisa.test.TestExec;
import com.itko.lisa.vse.stateful.model.ArgumentType;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.ArgumentType.Operator;
//import com.itko.lisa.vse.stateful.protocol.DataProtocol;
import com.itko.lisa.vse.stateful.protocol.ws.WSSOAPProtocolHandler;
import com.itko.util.Parameter;
import com.itko.util.ParameterList;

public class StubValidationDPH extends WSSOAPProtocolHandler {

	private static Logger logger = Logger.getLogger(StubValidationDPH.class);

	RawTrafficiParser xmlParser = new RawTrafficiParser();

	public StubValidationDPH() {
		//super();
		// System.setOut(outputFile("/opt/app/t1eam4c1/sltest/branches/b1503/1503_p1_Grid_On/1503_myATT_Recording_GridOn/1503_myATT_Recording_GridOn/VServices_Validation/debugLog/alloutSystem.txt"));
		// System.setErr(outputFile("/opt/app/t1eam4c1/sltest/branches/b1503/1503_p1_Grid_On/1503_myATT_Recording_GridOn/1503_myATT_Recording_GridOn/VServices_Validation/debugLog/alloutError.txt"));

	}

	protected PrintStream outputFile(String name) {
		try {
			return new PrintStream(new BufferedOutputStream(
					new FileOutputStream(name)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public void updateRequest(Request request) {
	 * 
	 * if (logger.isInfoEnabled()) {
	 * logger.info("updateRequest(Request request)"); }
	 * extractAttributes(request); }
	 */

	@Override
	public void updateRequest(TestExec testExec, Request request) {
		//request.setCharset("UTF-8");
		//request.setMatchTolerance(matchTolerance);
		System.out.println("Request   "+request.getBodyAsString());
		
		//request.
		
		
		super.updateRequest(testExec, request);
		System.out.println("Super called   ");

		if (logger.isInfoEnabled()) {
			logger.info("updateRequest(TestExec testExec, Request request)");
		}
		if (request != null)
			extractAttributes(request);

	}

	private boolean isCSIStubVlaidationRequestUodateDone(Request request) {
		ParameterList metaList = request.getMetaData();
		if (null != metaList
				&& null != metaList.getParameter("STUB_VALIDATION_KEY_TXID")) {

			Parameter p = metaList.getParameter("STUB_VALIDATION_KEY_TXID");
			String TranxID = p.getValue();
			ParameterList pList = new ParameterList();
			pList.setParameterValue("STUB_VALIDATION_KEY_TXID", TranxID);
			request.setMetaData(pList);
			ParameterList reqParam = request.getArguments();
			reqParam.addAll(pList);
			return true;
		}
		return false;
	}

	private void extractAttributes(Request request) {
		// this is for CSI
		// commented to add ipaddress in playback
		/*
		 * if(isCSIStubVlaidationRequestUodateDone(request)){ return; }
		 */

		// NON CSI web services
		ParameterList pList = new ParameterList();

		String TranxID = "";
		// String xmlRequestBody = request.getBodyText().toString();
		String xmlRequestBody = request.getBodyAsString();
		System.out.println("Body String 1" + request.toString());
		try {
			if (xmlRequestBody != null && !xmlRequestBody.isEmpty()) {
				/*
				 * TranxID = xmlParser
				 * .getTransactionIDFromRequestBody(xmlRequestBody);
				 */

				TranxID = getTransactionID(xmlRequestBody);
				if (TranxID != null && !TranxID.isEmpty()) {
					if (TranxID.equalsIgnoreCase("ParserError")) {
						TranxID = "STUB_VALIDATION_PARSERERROR";
						System.out.println(TranxID
								+ " :------------------------ ");
						System.out.println("Request Body : " + xmlRequestBody);
					}

				} else {
					TranxID = "STUB_VALIDATION_TXIDBLANK";
					System.out.println(TranxID + " :------------------------ ");
					System.out.println("Request Body : " + xmlRequestBody);

				}
			} else {
				TranxID = "STUB_VALIDATION_REQUESTBLANK";
				System.out.println("Request Body : " + TranxID);

			}
		} catch (Exception ex) {
			TranxID = "STUB_VALIDATION_DPHEXCEPTION";
			System.out.println(TranxID + " :------------------------ ");

			System.out.println("Request Body : " + xmlRequestBody);

		} finally {
			// pList.setParameterValue("STUB_VALIDATION_KEY_TXID", TranxID);

			// request.setMetaData(pList);

			ParameterList reqParam = request.getArguments();
			// reqParam.addAll(pList);
			System.out.println("Request Params=======>"
					+ reqParam.getAllKeyValuePairs());
			System.out.println("Adding HTTP_SESSIONID in StubValidationDPH  "
					+ TranxID);

			Parameter newParam = new Parameter("HTTP_SESSIONID", TranxID);
			ArgumentType type = new ArgumentType();
			// set new Matching criteria
			type.setOperator(Operator.ANY);
			// set TYPE
			newParam.setType(type);
			reqParam.addParameter(newParam);
			// System.out.println("Finally TXID : " + TranxID);
		}
	}

	private String getTransactionID(String xmlRequestBody)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String TXID = null;

		if (xmlRequestBody.contains("<mes:messageId>")) {
			String splitMsgId[] = xmlRequestBody.split("<mes:messageId>");
			// m_logger.info("splitMsgId 1==>" + splitMsgId[0]);
			// m_logger.info("splitMsgId 2==>" + splitMsgId[1]);
			if (splitMsgId.length > 0) {
				String splitMsgId2[] = splitMsgId[1].split("</mes:messageId>");
				TXID = splitMsgId2[0];
				System.out.println("TXID   " + TXID);
			}

		} else {
			TXID = xmlParser.getTransactionIDFromRequestBody(xmlRequestBody);
		}
		return TXID;
	}

}
