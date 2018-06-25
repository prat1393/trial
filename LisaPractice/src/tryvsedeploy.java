import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Properties;

import com.ibm.icu.util.BytesTrie.Iterator;
import com.itko.lisa.vse.batch.cmd.mgr.VSEManagerWrapper;
import com.itko.lisa.vse.batch.cmd.mgr.VSEManagerWrapper_try;

public class tryvsedeploy {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File file = new File("C:\\agile1711.git\\agile1711\\1711\\stubbing\\trunk\\IST_Deploy\\MYATT_DSS_COMBINE_2014_FINAL\\vse-ant.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		Enumeration enuKeys = properties.keys();
		System.out.println(enuKeys);
		while (enuKeys.hasMoreElements()) {
			String key = (String) enuKeys.nextElement();
			String value = properties.getProperty(key);
			System.out.println(key + ": " + value);
			
	}
		LinkedList<String> l1=new LinkedList<String>();
		l1.clear();
		l1.add("project-status-vse");
		l1.add("--registry");
		l1.add(properties.getProperty("lisa.registry"));
		l1.add("--vse");
		l1.add(properties.getProperty("vse.server"));
		l1.add("--vs-dir");
		l1.add(properties.getProperty("lisa.proj.root"));
		l1.add("admin");
		l1.add("admin");
		l1.add("-Xms256m");
		l1.add("-Xmx1024m");
		
		java.util.Iterator<String> i = l1.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}

		
		VSEManagerWrapper_try vw=new VSEManagerWrapper_try(l1);

}
}
