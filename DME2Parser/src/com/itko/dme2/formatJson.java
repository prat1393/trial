package com.itko.dme2;

import org.json.JSONException;
import org.json.JSONObject;

public class formatJson {
	public String format(String js) throws JSONException
	{
		
		String indented = (new JSONObject(js)).toString(4);
		System.out.println(indented);
	String jo=indented.replaceAll("[\\t\\n\\r\\s]+","");
	System.out.println(jo);
		return jo;
	}

}
