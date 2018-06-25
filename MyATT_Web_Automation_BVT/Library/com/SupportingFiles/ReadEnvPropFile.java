/**************************************************************
   Class Name 			- ReadEnvPropFile
   Description			- Class for reading Env.Properties file
   Date created 		- 22-Dec-14
   Developed by 		- Rahul Bakde
   Last Modified By		-
   Last Modified Date 	-
***************************************************************/

package com.SupportingFiles;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ReadEnvPropFile {

	//Variable declaration
	String sResult = "",result1="";
	String propFileName = "Env.Properties";

	static Properties oProp = new Properties();
	
	//Constructor ReadEnvPropFile for creating InputStream object
	public ReadEnvPropFile() throws Exception{
		InputStream oInputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (oInputStream != null) {
			oProp.load(oInputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
	}
	
	/**************************************************************
	   Method Name 			- Release()
	   Description			- This method is used to retrieve the Release value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String Release(){
		
		//Storing the value of 'Release' from the Evn.Properties file
		String sRelease = oProp.getProperty("Release");
		
		return sRelease;
	}
	
	/**************************************************************
	   Method Name 			- sApplication()
	   Description			- This method is used to retrieve the sApplication value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String Application(){
		
		//Storing the value of 'Application' from the Evn.Properties file
		String sApplication = oProp.getProperty("Application");
		
		return sApplication;
	}

	/**************************************************************
	   Method Name 			- myATTEnvURL()
	   Description			- This method is used to retrieve the envURL value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String MyATTEnvURL(){
		
		//Storing the value of 'MyATTEnvURL' from the Evn.Properties file
		String sEnvURL = oProp.getProperty("MyATTEnvURL");
		
		return sEnvURL;
	}
	
	/**************************************************************
	   Method Name 			- myATTZoneEnvURL()
	   Description			- This method is used to retrieve the envURL value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String MyATTZoneEnvURL(){
		
		//Storing the value of 'MyATTZoneEnvURL' from the Evn.Properties file
		String sEnvURL = oProp.getProperty("MyATTZoneEnvURL");
		
		return sEnvURL;
	}
	
	/**************************************************************
	   Method Name 			- testDataFile()
	   Description			- This method is used to retrieve the testDataFile value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String TestDataFile(){
		
		//Storing the value of 'TestDataFile' from the Evn.Properties file
		String sTestDataFile = oProp.getProperty("TestDataFile");
		
		return sTestDataFile;
	}
	
	/**************************************************************
	   Method Name 			- objTimeOut()
	   Description			- This method is used to retrieve the objTimeOut value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String ObjTimeOut(){
		
		//Storing the value of 'ObjTimeOut' from the Evn.Properties file
		String sObjTimeOut = oProp.getProperty("ObjTimeOut");
		
		return sObjTimeOut;
	}
	
	/**************************************************************
	   Method Name 			- executedBy()
	   Description			- This method is used to retrieve the executedBy value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String ExecutedBy(){
		
		//Storing the value of 'Executed_By' from the Evn.Properties file
		String sExecutedBy = oProp.getProperty("Executed_By");
		
		return sExecutedBy;
	}
	
	/**************************************************************
	   Method Name 			- sendMail()
	   Description			- This method is used to retrieve the sendMail value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String SendMail(){
		
		//Storing the value of 'SendMail' from the Evn.Properties file
		String sSendMail = oProp.getProperty("SendMail");
		
		return sSendMail;
	}
	
	/**************************************************************
	   Method Name 			- sendTo()
	   Description			- This method is used to retrieve the sendTo value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String SendTo(){
		
		//Storing the value of 'SendTo' from the Evn.Properties file
		String sSendTo = oProp.getProperty("SendTo");
		
		return sSendTo;
	}
	
	/**************************************************************
	   Method Name 			- mailCC()
	   Description			- This method is used to retrieve the mailCC value from the Env.Properties file
	   Date created 		- 22-Dec-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String MailCC(){
		
		//Storing the value of 'MailCC' from the Evn.Properties file
		String sMailCC = oProp.getProperty("MailCC");
		
		return sMailCC;
	}
	
	/**************************************************************
	   Method Name 			- attuidMyATTZone()
	   Description			- This method is used to retrieve the attuidMyATTZone value from the Env.Properties file
	   Date created 		- 14-Jan-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String AttuidMyATTZone(){
		
		//Storing the value of 'attuidMyATTZone' from the Evn.Properties file
		String sAttUidMyATTZone = oProp.getProperty("attuidMyATTZone");
		
		return sAttUidMyATTZone;		
	}
	
	/**************************************************************
	   Method Name 			- passwordMyATTZone()
	   Description			- This method is used to retrieve the passwordMyATTZone value from the Env.Properties file
	   Date created 		- 14-Jan-14
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String PasswordMyATTZone(){
		
		//Storing the value of 'passwordMyATTZone' from the Evn.Properties file
		String sPasswordMyATTZone = oProp.getProperty("passwordMyATTZone");
		
		return sPasswordMyATTZone;		
	}
	
	/**************************************************************
	   Method Name 			- updateTCStatusInQC()
	   Description			- This method is used to retrieve the updateTCStatusInQC value from the Env.Properties file
	   Date created 		- 27-Jan-15
	   Developed by 		- Rahul Bakde
	   Last Modified By		-
	   Last Modified Date 	-
	***************************************************************/
	public String UpdateTCStatusInQC(){
		
		//Storing the value of 'UpdateTCStatusInQC' from the Evn.Properties file
		String sUpdateTCStatusInQC = oProp.getProperty("UpdateTCStatusInQC");
		return sUpdateTCStatusInQC;
	}
	
	public static String ReportsInFolders(){

		return oProp.getProperty("ReportsInFolders");
	}
}
