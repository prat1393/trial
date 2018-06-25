package com.itko.lisa.ext.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ResponseParser {	
			
	public static String removeAttrFromResponse(String response,String xPath) throws Exception{
		if (null != response && null != xPath) {
			Document respDoc = stringToDom(response);
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(xPath);
			Node targetNode = (Node) expr.evaluate(respDoc,XPathConstants.NODE);
			if (null != targetNode) {
				Node parentNode = targetNode.getParentNode();
				parentNode.removeChild(targetNode);
				response = domToString(respDoc);
			}else{
				System.out.println("XPath query fail to locate the position.");
			}
		} else {
			System.out.println("removeAttrFromResponse:One of the Inputvalue is null");
		}
		return response;
	}
	
	public static String addAttrToResponse(String response,String xPath,String xPathValue) throws Exception{
		if (null != response && null != xPathValue && null != xPath) {
			Document respDoc = stringToDom(response);
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(xPath);
			Node insertBeforeNode = (Node) expr.evaluate(respDoc,XPathConstants.NODE);
			if (null != insertBeforeNode) {
				Document newAttrDoc = stringToDom(xPathValue);
				Element ele = newAttrDoc.getDocumentElement();
				Node secondNode = respDoc.importNode(ele, true);
				insertBeforeNode.getParentNode().insertBefore(secondNode, insertBeforeNode);
				response = domToString(respDoc);
			}else{
				System.out.println("XPath query fail to locate the position.");
			}
		} else {
			System.out.println("addAttrToResponse:one of the Inputvalue is null");			
		}
		return response;
	}
	
	public static String updateAttrToResponse(String response,String xPath,String xPathValue) throws Exception{
		if (null != response && null != xPathValue && null != xPath) {
			Document respDoc = stringToDom(response);
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(xPath);
			Node targetNode = (Node) expr.evaluate(respDoc,XPathConstants.NODE);
			if (null != targetNode) {
				Node parentNode = targetNode.getParentNode();				
				Document newAttrDoc = stringToDom(xPathValue);
				Element ele = newAttrDoc.getDocumentElement();
				Node newNode = respDoc.importNode(ele, true);
				parentNode.replaceChild(newNode, targetNode);
				response = domToString(respDoc);
			}else{
				System.out.println("XPath query fail to locate the position.");
			}
		} else{
			System.out.println("updateAttrToResponse:one of the Inputvalue is null");
		}
		return response;
	}
	
	public static String domToString(Document respDoc) throws Exception{
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(respDoc);		
		StringWriter strWriter = new StringWriter();
		StreamResult result = new StreamResult(strWriter);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(source, result);
		return strWriter.toString();
	}
	
	public static Document stringToDom(String appendNode) throws Exception {
		DocumentBuilderFactory dom = DocumentBuilderFactory.newInstance();
		DocumentBuilder domB = dom.newDocumentBuilder();
		org.w3c.dom.Document doc = domB.parse(new InputSource(new StringReader(
				appendNode)));
		return doc;
	}
}
