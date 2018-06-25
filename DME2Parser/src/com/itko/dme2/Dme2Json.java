package com.itko.dme2;


import org.json.JSONException;
import org.json.JSONObject;

import com.itko.lisa.simulator.Simulator;
import com.itko.lisa.test.ITestExec;
import com.itko.lisa.test.TestCase;
import com.itko.lisa.test.TestExec;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.util.ParameterList;
import com.codahale.metrics.Metric;
public class Dme2Json {

	String run="Run";
	
	Simulator sim = null;
	int instance=1;
	int robot=0;
	public String dme2parse(String in)
	{
		String wquot=in.replaceAll("&quot;","\"");
		
		System.out.println(wquot);
		TestExec te =new TestExec(test, run, sim, instance, robot);
		String bv=te.parseInState(wquot);
		System.out.println(bv);
		return bv;
	}

}
