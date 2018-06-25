package com.itko.lisa.ext.vse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.itko.util.Parameter;

public class RawTrafficiParser {

	private static Logger logger = Logger.getLogger(RawTrafficiParser.class);
	private static DocumentBuilderFactory sFactory;
	private static Parameter p;

	private static final XPathExpression xmlElementPath;

	static {

		try {
			sFactory = DocumentBuilderFactory.newInstance();
			sFactory.setIgnoringComments(true);
			sFactory.setValidating(false);
			sFactory.setNamespaceAware(true);
			sFactory.setFeature("http://xml.org/sax/features/namespaces", false);
			sFactory.setFeature("http://xml.org/sax/features/validation", false);
			sFactory.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
					false);
			sFactory.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-external-dtd",
					false);

			XPathFactory xPathFactory = XPathFactory.newInstance();
			xmlElementPath = xPathFactory.newXPath().compile("//*");
		} catch (XPathExpressionException e) {
			throw new RuntimeException("Bad XPath syntax", e);
		} catch (Exception e) {
			throw new RuntimeException("Bad XPath syntax", e);
		}

	}

	public static String getTransactionIDFromRequestBody(String requestXML)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		String lsTransactionId = "";
		try {
			DocumentBuilder parser = sFactory.newDocumentBuilder();
			Document reqDoc = parser.parse(new InputSource(new StringReader(
					requestXML)));

			Element msgHeadNodeOtherThanCSI = (Element) reqDoc
					.getElementsByTagName(
							"txIdNS:MessageHeader_STUB_VALIDATION_KEY_TXID")
					.item(0);
			if (msgHeadNodeOtherThanCSI != null) {
				lsTransactionId = msgHeadNodeOtherThanCSI.getFirstChild()
						.getNodeValue();
			}

			Element msgHeadNode = (Element) reqDoc.getElementsByTagName(
					"mes:TrackingMessageHeader").item(0);
			if (msgHeadNode != null) {
				Node messageIdNode = msgHeadNode.getElementsByTagName(
						"cin:messageId").item(0);
				if (messageIdNode.getFirstChild().getNodeValue() != null) {
					lsTransactionId = messageIdNode.getFirstChild()
							.getNodeValue();
				}
			}
		} catch (Exception ex) {
			lsTransactionId = "ParserError";
		}
		return lsTransactionId;
	}

	public static void main(String[] args) throws Exception {
		StringBuffer sb = new StringBuffer();
		try {
			FileReader file = new FileReader(
					"C:\\dummy\\EJB_01_120516_2201AMCST.xml");

			BufferedReader br = new BufferedReader(file);
			String str = null;
			while ((str = br.readLine()) != null)

			{
				sb.append(str);

			}
			System.out.println("str " + sb.toString());
			System.out.println("TXID= "
					+ getTransactionIDFromRequestBody(sb.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getConversationIdByxmlString(String responseXML)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		String lsConvID;
		lsConvID = "";
		try {
			String responseXMLToParse = "";
			if (responseXML.contains("messageId")
					|| responseXML
							.contains("txIdNS:MessageHeader_STUB_VALIDATION_KEY_TXID")) {
				String[] xmlArray = responseXML.split("<\\?xml");
				responseXMLToParse = "<?xml" + xmlArray[1];

				DocumentBuilder parser = sFactory.newDocumentBuilder();
				Document doc = parser.parse(new InputSource(new StringReader(
						responseXMLToParse)));
				Element msgHeadNode = (Element) doc.getElementsByTagName(
						"messageId").item(0);
				if (msgHeadNode == null) {
					Element msgHeadNodeOtherThanCSI = (Element) doc
							.getElementsByTagName(
									"txIdNS:MessageHeader_STUB_VALIDATION_KEY_TXID")
							.item(0);
					if (msgHeadNodeOtherThanCSI == null) {
						lsConvID = "";
					} else {
						lsConvID = msgHeadNodeOtherThanCSI.getFirstChild()
								.getNodeValue();
					}
				} else {
					lsConvID = msgHeadNode.getFirstChild().getNodeValue();
				}
			}
		} catch (Exception ex) {
			lsConvID = "ParserError";
		}
		return lsConvID;
	}
}