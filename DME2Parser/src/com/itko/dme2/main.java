package com.itko.dme2;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;

public class main {

	public static void main(String[] args) throws JSONException, IOException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("what do you want to do");
		System.out.println("1. dme2parse");
		System.out.println("2.json2dme");
		lisamagicstringparser ls=new lisamagicstringparser();
		jsontodme2 jd=new jsontodme2();
	int f=sc.nextInt();
	if(f==1)
	{
		
	ls.msparse();
	}
		if(f==2)
		{
			jd.convert();
		}

	}

}
