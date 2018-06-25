package com.itko.dme2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class jsontodme2 {

	public  void convert() throws IOException {
		// TODO Auto-generated method stub
		File f =new File("H://dme2/jsonoutput.txt");
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



	
		String v=a.replaceAll("[\\t\\n\\r\\s]+","");
		System.out.println(v);
		String ad=v.replaceAll("\"", "&quot;");
		System.out.println(ad);
	}

}
