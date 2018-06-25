
package com.itko.dme2;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.itko.lisa.simulator.Simulator;
import com.itko.lisa.test.ITestExec;
import com.itko.lisa.test.TestCase;
import com.itko.lisa.test.TestExec;
import com.codahale.metrics.Metric;



public class dme2parse{
	
	

	public static void main(String[] args) throws IOException, JSONException {
		TestCase test = new TestCase();
		String run="Run";
		Simulator sim = null;
		int instance=1;
		int robot=0;
		// TODO Auto-generated method stub
		File f =new File("H://dme2/json.txt");
		//FileInputStream fis=new FileInputStream(f);
		//BufferedInputStream br =new BufferedInputStream(fis);
FileReader fr=new FileReader(f);
BufferedReader br=new BufferedReader(fr);
String a="";
String sc=br.readLine();
while (sc!=null)
{
	a=a+sc;
	sc=br.readLine();
}
br.close();
fr.close();

String wquot=a.replaceAll("&quot;","\"");

System.out.println(wquot);
TestExec te =new TestExec(test, run, sim, instance, robot);
String bv=te.parseInState(wquot);
System.out.println(bv);

String result=bv.substring(bv.indexOf("<string>")+8, bv.indexOf("</string>"));
System.out.println("Rsult: "+ result);
String indented = (new JSONObject(result)).toString(4);
System.out.println(indented);
System.out.println(indented.replaceAll("[\\t\\n\\r\\s]+",""));


String xml = XML.toString(new JSONObject(indented));
System.out.println(xml);
//String te=TestExec.parseInState(wquot);
	}

}
