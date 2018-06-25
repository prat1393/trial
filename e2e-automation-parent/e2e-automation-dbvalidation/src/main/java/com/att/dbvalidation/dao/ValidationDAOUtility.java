package com.att.dbvalidation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationDAOUtility {
	
	 public static Connection con_EZ1=null;	
	 public static Connection con_EZ2=null;	
	 public static Connection con_EZ3=null;	
	 public static Connection con_OMS1=null;	
	 public static Connection con_OMS2=null;	
	 public static Connection con_OMS3=null;	
	 public static Connection con_CCR=null;	
	 public static Connection con_MPS=null;	
	 public static Connection con_Batch=null;	
	 public static Connection con_BDS=null;
	 public static Connection con_BDS_Next=null;
	 
	 public static Connection con_TLG_DLS=null;	
	 public static Connection con_TLG_PAC=null;	
	 public static Connection con_TLG_SAN=null;		
	 public static Connection con_TLG_MWR=null;	
	 public static Connection con_TLG_GAC=null;	
	 public static Connection con_TLG_GLR=null;	
	 public static Connection con_TLG_NBI=null;	
	 public static Connection con_TLG_NWS=null;	
	 
	
	 public static Connection con_ENB_DLS=null;	
	 public static Connection con_ENB_GAC=null;	
	 public static Connection con_ENB_MWR=null;	
	 public static Connection con_ENB_NBI=null;	
	 public static Connection con_ENB_PAC=null;	
	 
	 public static Connection con=null;	
	 
	 public static ResultSet res=null;
	 public static PreparedStatement ps=null;	 
	 
	 public static void checkoracleConnection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con= DriverManager.getConnection(DB[0], DB[1], DB[2]);	
					System.out.println("Connected");
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				e.printStackTrace();
			}
		}	 
	 public static void checkdb2Connection(String[] DB){
			try { 
				System.out.println("Check DB2 Connection");
					Class.forName("com.ibm.db2.jcc.DB2SimpleDataSource"); 
					con= DriverManager.getConnection(DB[0], DB[1], DB[2]);	
					System.out.println("Connected BD2");
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				System.out.println(" Not Connected BD2");
				e.printStackTrace();
			}
		}	
	 
	 public static void CCRConnection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_CCR = DriverManager.getConnection(DB[0], DB[1], DB[2]);	
					System.out.println(" CCR Connected");
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				e.printStackTrace();
			}
		}
	 
	 public static void OMS1Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_OMS1 = DriverManager.getConnection(DB[0], DB[1], DB[2]);
					System.out.println(" OMS1 Connected");
					//LoggerWrapper.logger.info("Connected to OMS Seg1 DB");
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				System.out.println(" OMS1 Not Connected");
				//LoggerWrapper.logger.info("Exception :: OMS Seg1 Database Connection Error: "+ e);
			}
		}
		public static void OMS2Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_OMS2 = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					System.out.println(" OMS2 Connected");
					//LoggerWrapper.logger.info("Connected to OMS Seg2 DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: OMS Seg2 Database Connection Error: "+ e);
			}
		}
		public static void OMS3Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_OMS3 = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					System.out.println(" OMS3 Connected");
					//LoggerWrapper.logger.info("Connected to OMS Seg3 DB");					
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: OMS Seg3 Database Connection Error: "+ e);
			}
		}
		
		public static void EZ1Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_EZ1 = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					System.out.println(" Enabler1 Connected");
					//LoggerWrapper.logger.info("Connected to Enabler Zone1 DB");		
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: Enabler Zone1 Database Connection Error: "+ e);
			}
		}
		public static void EZ2Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_EZ2 = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					System.out.println(" Enabler2 Connected");
					//LoggerWrapper.logger.info("Connected to Enabler Zone2 DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				System.out.println(" Enabler2 Not Connected");
				//LoggerWrapper.logger.info("Enabler Zone2 Database Connection Error: "+ e);
			}
		}
		public static void EZ3Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_EZ3 = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					System.out.println(" Enabler3 Connected");
					//LoggerWrapper.logger.info("Connected to Enabler Zone3 DB");			
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Enabler Zone3 Database Connection Error: "+ e);
			}
		}
		public static void MPSConnection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_MPS = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					System.out.println(" MPS Connected");
					//LoggerWrapper.logger.info("Connected to MPS DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				System.out.println(" MPS Not Connected");
				//LoggerWrapper.logger.info("Exception :: MPS Database Connection Error: "+ e);
			}
		}
	 
		public static void BatchConnection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_Batch= DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to Batch DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: Batch Database Connection Error: "+ e);
			}
		}
		public static void BDSConnection(String[] DB){
			try { 
					Class.forName("com.ibm.db2.jcc.DB2SimpleDataSource"); 
					con_BDS = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to BDS DB");			
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: BDS Database Connection Error: "+ e);
			}
		}
		public static void BDSConnectionNext(String[] DB){
			try { 
					Class.forName("com.ibm.db2.jcc.DB2SimpleDataSource"); 
					con_BDS_Next = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to BDS DB of Next Release");			
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: BDS Database Connection Error: "+ e);
			}
		}
///////****************************Enabler Wireless DB Connections**********************///////////////////////////
		
		public static void ENB_DLS_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_ENB_DLS = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to ENB_DLS DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: ENB_DLS Database Connection Error: "+ e);
			}
		}
		public static void ENB_GAC_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_ENB_GAC = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to ENB_GAC DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: ENB_GAC Database Connection Error: "+ e);
			}
		}
		public static void ENB_MWR_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_ENB_MWR = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to ENB_MWR DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: ENB_MWR Database Connection Error: "+ e);
			}
		}
		public static void ENB_NBI_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_ENB_NBI = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to ENB_NBI DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: ENB_NBI Database Connection Error: "+ e);
			}
		}
		public static void ENB_PAC_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_ENB_PAC = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to ENB_PAC DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: ENB_PAC Database Connection Error: "+ e);
			}
		}
///////****************************TLG Wireless DB Connections**********************///////////////////////////		
		public static void TLG_DLS_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_TLG_DLS = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to TLG_DLS DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: MPS Database Connection Error: "+ e);
			}
		}
		public static void TLG_PAC_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_TLG_PAC = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to TLG_PAC DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: MPS Database Connection Error: "+ e);
			}
		}
		public static void TLG_SAN_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_TLG_SAN = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to TLG_SAN DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: TLG_SAN Database Connection Error: "+ e);
			}
		}
		public static void TLG_MWR_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_TLG_MWR = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to TLG_MWR DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: TLG_MWR Database Connection Error: "+ e);
			}
		}
		public static void TLG_GAC_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_TLG_GAC= DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to TLG_GAC DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: TLG_GAC Database Connection Error: "+ e);
			}
		}
		public static void TLG_GLR_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_TLG_GLR = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to TLG_GLR DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: TLG_GLR Database Connection Error: "+ e);
			}
		}
		public static void TLG_NBI_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_TLG_NBI = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to TLG_NBI DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: TLG_NBI Database Connection Error: "+ e);
			}
		}
		public static void TLG_NWS_Connection(String[] DB){
			try { 
					Class.forName("oracle.jdbc.OracleDriver"); 
					con_TLG_NWS = DriverManager.getConnection(DB[0], DB[1], DB[2]); 
					//LoggerWrapper.logger.info("Connected to TLG_NWS DB");				
			 } 
			catch (ClassNotFoundException | SQLException e) { 
				//LoggerWrapper.logger.info("Exception :: TLG_NWS Database Connection Error: "+ e);
			}
		}
		
		public static void ClosingConnection()
		{
			try {
				if (con_OMS1!=null) {
					con_OMS1.close();
					con_OMS1=null;
					//LoggerWrapper.logger.info("OMS1 CLosed");
				}
				if (con_OMS2!=null) {
					con_OMS2.close();
					con_OMS2=null;
					//LoggerWrapper.logger.info("OMS2 CLosed");
				}
				if (con_OMS3!=null) {
					con_OMS3.close();
					con_OMS3=null;
					//LoggerWrapper.logger.info("OMS3 CLosed");
				}
				
				if (con_CCR!=null) {
					con_CCR.close();
					con_CCR=null;
					System.out.println("Disconnected CCR");
					//LoggerWrapper.logger.info("CCR CLosed");
				}
				if (con_EZ1!=null) {
					con_EZ1.close();
					con_EZ1=null;
					//LoggerWrapper.logger.info("Enabler1 CLosed");
				}
				if (con_EZ2!=null) {
					con_EZ2.close();
					con_EZ2=null;
					//LoggerWrapper.logger.info("Enabler2 CLosed");
				}
				if (con_EZ3!=null) {
					con_EZ3.close();
					con_EZ3=null;
					//LoggerWrapper.logger.info("Enabler3 CLosed");
				}
				if (con_MPS!=null) {
					con_MPS.close();
					con_MPS=null;
					//LoggerWrapper.logger.info("MPS CLosed");
				}
				if (con_Batch!=null) {
					con_Batch.close();
					con_Batch=null;
					//LoggerWrapper.logger.info("Batch CLosed");
				}
				if (con_TLG_DLS!=null) {
					con_TLG_DLS.close();
					con_TLG_DLS=null;
					//LoggerWrapper.logger.info("TLG_DLS CLosed");
				}
				if (con_TLG_PAC!=null) {
					con_TLG_PAC.close();
					con_TLG_PAC=null;
					//LoggerWrapper.logger.info("TLG_PAC CLosed");
				}
				if (con_TLG_SAN!=null) {
					con_TLG_SAN.close();
					con_TLG_SAN=null;
					//LoggerWrapper.logger.info("TLG_SAN CLosed");
				}
				if (con_TLG_MWR!=null) {
					con_TLG_MWR.close();
					con_TLG_MWR=null;
					//LoggerWrapper.logger.info("TLG_MWR CLosed");
				}
				if (con_TLG_GAC!=null) {
					con_TLG_GAC.close();
					con_TLG_GAC=null;
					//LoggerWrapper.logger.info("TLG_GAC CLosed");
				}
				if (con_TLG_GLR!=null) {
					con_TLG_GLR.close();
					con_TLG_GLR=null;
					//LoggerWrapper.logger.info("TLG_GLR CLosed");
				}
				if (con_TLG_NBI!=null) {
					con_TLG_NBI.close();
					con_TLG_NBI=null;
					//LoggerWrapper.logger.info("TLG_NBI CLosed");
				}
				if (con_TLG_NWS!=null) {
					con_TLG_NWS.close();
					con_TLG_NWS=null;
					//LoggerWrapper.logger.info("TLG_NWS CLosed");
				}
				if (con_ENB_DLS!=null) {
					con_ENB_DLS.close();
					con_ENB_DLS=null;
					//LoggerWrapper.logger.info("ENB_DLS CLosed");
				}
				if (con_ENB_GAC!=null) {
					con_ENB_GAC.close();
					con_ENB_GAC=null;
					//LoggerWrapper.logger.info("ENB_GAC CLosed");
				}
				if (con_ENB_MWR!=null) {
					con_ENB_MWR.close();
					con_ENB_MWR=null;
					//LoggerWrapper.logger.info("ENB_MWR CLosed");
				}
				if (con_ENB_NBI!=null) {
					con_ENB_NBI.close();
					con_ENB_NBI=null;
					//LoggerWrapper.logger.info("ENB_NBI CLosed");
				}
				if (con_ENB_PAC!=null) {
					con_ENB_PAC.close();
					con_ENB_PAC=null;
					//LoggerWrapper.logger.info("ENB_PAC CLosed");
				}	
			} catch (SQLException e) {
				//LoggerWrapper.logger.info("Exception :: SQLExceptions :"+e);
			}
		}
}
