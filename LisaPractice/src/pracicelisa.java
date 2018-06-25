import java.io.File;

import com.itko.lisa.coordinator.TestRegistry;
import com.itko.lisa.coordinator.VirtualServiceEnvironment;
import com.itko.lisa.model.mar.ModelArchive;
import com.itko.lisa.test.Environment;
import com.itko.lisa.test.LisaSecurityManager;
import com.itko.lisa.vse.batch.cmd.mgr.VSEManagerWrapper;
import com.jniwrapper.DefaultLibraryLoader;
import com.itko.activemq.ActiveMQConnectionFactory;
public class pracicelisa {
	private static VirtualServiceEnvironment ve;
	String registryName="tcp://localhost:2010/Registry";
	String environmentName="VSE";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		  System.setProperty("LISA_HOME","C:\\lisa 8.4\\DevTest");
		//  System.setProperty("lisa.registry","tcp\\://localhost\\:2010/Registry");
		  
		  System.setProperty("JAVA_HOME", "C:\\Program Files\\Java\\jdk1.8.0_161\\jre\\bin");
		//DefaultLibraryLoader.getInstance().addPath("C:\\lisa 8.4\\DevTest\\bin");
new Environment("VSE Manager for batch operations", 9);
		
	      Environment.banner("LISA Virtual Service Environment Manager - The Command Line Tool for Managing VSEs.");
	      String text = Environment.getBrand() + " Version :: " + Environment.getVersionString();
	      System.out.println(text);
	      //System.out.println(Environment.getTestRegistryFullName());
	/*	File f=new File("H:\\devtest files\\New_Project_00\\VirtualServices\\kapil.vsi.mar");
		ModelArchive ma=new ModelArchive(f);
		VSEManagerWrapper vw=new VSEManagerWrapper();*/
	/*	
		TestRegistry tr=new TestRegistry();
	String a=com.itko.lisa.test.Environment.getTestRegistryFullName();
		System.out.println(a);
		*/
		pracicelisa pl=new pracicelisa();
		pl.ensureTheRegistry();
	}
	  private void ensureTheRegistry()
			    throws Exception
			  {
		  pracicelisa p2=new pracicelisa();
		  System.out.println("a");
			    if (Environment.getTestRegistry() == null)
			    {
			    	  System.out.println("ab");
			    	  System.out.println(this.registryName);
			      TestRegistry tr = Environment.setTestRegistry(registryName);
			      System.out.println("ad");
			      if (tr == null) {
			    	  System.out.println("ac");
			        throw new IllegalArgumentException("Could not connect to LISA registry with name " + registryName + ".");
			      }
			      p2.checkACL();
			      System.out.println("Connected to registry : " + registryName);
			    }
			  }
	  private void checkACL()
			    throws Exception
			  {
			    TestRegistry reg = Environment.getTestRegistry();
			    System.out.println("idhar");
			    if (reg.isSecurityEnabled()) {
			      LisaSecurityManager.authenticateUser("admin","admin");
			      System.out.println("i am here");
			    }
			  }
	 }
