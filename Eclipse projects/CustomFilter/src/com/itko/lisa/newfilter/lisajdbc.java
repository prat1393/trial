package com.itko.lisa.newfilter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class lisajdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
	Scanner sc=new Scanner(System.in);
	int ci=sc.nextInt();
	if(ci>2)
	{
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection con=DriverManager.getConnection(  
				"jdbc:derby://localhost:1529/lisa-demo-server.db","sa","sa");
		Statement stmt=con.createStatement();
		 rs=stmt.executeQuery("select * from users");
		 ResultSetMetaData metadata = rs.getMetaData();
		 
		 int  j=metadata.getColumnCount();
		 while(rs.next())
		 {
			 String row = "";
		        for (int i = 1; i <= j; i++) {
		            row += rs.getString(i) + ", ";          
		        }
		        System.out.println(row);
			 
		 }
	}

	}

}
