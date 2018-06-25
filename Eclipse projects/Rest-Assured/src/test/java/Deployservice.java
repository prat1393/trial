
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.itko.lisa.model.mar.ModelArchiveInfo;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class Deployservice {
	static Scanner s=new Scanner(System.in);
	static String modelFile;
	static String configFile="project.config";
	static int capacity=1;
	static int thinkFactor=0;
	static Boolean autoRestartObject=true;
	 static ModelArchiveInfo mari;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 List<String> vsmname = new ArrayList<String>();
File f1=new File("C:\\lisa 8.4\\DevTest\\examples\\VServices\\Images") ;
vsmname=getAllFiles(f1);
deployfile(vsmname);


	}
	private static List<String> getAllFiles(File curDir) {
		 List<String> names = new ArrayList<String>();
				 File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.isDirectory())
                getAllFiles(f);
            if(f.isFile()){
            	
/*                System.out.println(f.getName());
*/                names.add(f.getName());
                
            }
          
        }
		return names;


	}
	private static void deployfile(List<String> nu)
	{
		
		System.out.println("Enter vsm to be deployed");
		String vsmname1=s.next()+".vsm";
		System.out.println(vsmname1);
		List<String> name=nu;
		/*Iterator it=name.iterator();
		System.out.println(name.size());
		while(it.hasNext())
		{
			System.out.println(it.next());
		}*/
		if(name.contains(vsmname1))
		{
			System.out.println("found");
			if(!name.contains(vsmname1+".marinfo"))
			{
				mari=createmar(vsmname1);
			}
			else
			{
				deployvsm(vsmname1);
			}
			
		}
		else
		{
			System.out.println("not found");
		
		
	}
}
	public static void deployvsm(String vsmodel)
	{
	
		RestAssured.baseURI="http://localhost:1505/api/Dcm/";
		RequestSpecification req=RestAssured.given();
		
		
	}
	public static  ModelArchiveInfo createmar(String vsmodel)
	{
		return ModelArchiveInfo.createVSMARInfo(modelFile, configFile, capacity, thinkFactor, autoRestartObject);	
		
		
		
	}
}
