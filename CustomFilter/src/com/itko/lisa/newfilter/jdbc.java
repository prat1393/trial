package com.itko.lisa.newfilter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class jdbc {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		ResultSet rs1;
		Document dc;
		int i=s.nextInt();
	
			jdbc j=new jdbc();
			dc=j.start(i);
			 TransformerFactory tranFactory = TransformerFactory.newInstance();
			    Transformer aTransformer = tranFactory.newTransformer();
			    Source src = new DOMSource(dc);
			    String abc;
			    Result dest = new StreamResult(System.out);
			    aTransformer.transform(src, dest);		

}
	
	public Document start(int j) throws ParserConfigurationException, SAXException, IOException, TransformerException

	{
		Document doc=null;
		if(j>2)
		{
		ResultSet rs = null;
		 Document mapDoc = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@zlt09211.vci.att.com:1524:t1c4d284","rr998s","December_17");
			Statement stmt=con.createStatement();  
			 rs=stmt.executeQuery("select member_num,member_name,domain_id from member where member_name like 'qay_slid_321%'");
			 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			 DocumentBuilder builder        = factory.newDocumentBuilder();
			  doc                   = builder.newDocument();
			 Element results = doc.createElement("Results");
				 doc.appendChild(results);
			 ResultSetMetaData rsmd = rs.getMetaData();
			   int  colCount           = rsmd.getColumnCount();

			 while (rs.next())
			 {
				   Element row = doc.createElement("Row");
				   results.appendChild(row);
				   for (int i = 1; i <= colCount; i++)
				   {
				      String columnName = rsmd.getColumnName(i);
				      System.out.println(columnName);
				      Object value      = rs.getObject(i);

				      Element node      = doc.createElement(columnName);
				      node.appendChild(doc.createTextNode(value.toString()));
				      System.out.println(value.toString());
				      row.appendChild(node);
				   }
				   System.out.println(doc);
			 } 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
		return doc;
	}
	
}
