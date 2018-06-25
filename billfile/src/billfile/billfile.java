package billfile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.lang3.StringEscapeUtils;
public class billfile {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String req;
		String req1;
		FileReader F1 = null;
		FileReader F4 = null;
		FileWriter F2 = null;
		FileWriter F3=null;
		BufferedReader br = null;
		BufferedReader br1 = null;
		BufferedWriter bw = null;
		BufferedWriter bw1 = null;
		String s1;
		String s2="";
		try {
			F1 = new FileReader("C://data.txt");
			
			br1 =new BufferedReader(F1);
			F2=new FileWriter("C://output.txt");
			bw=new BufferedWriter(F2);
			F3=new FileWriter("C://temp.txt");
			F4=new FileReader("C://temp.txt");
			bw1=new BufferedWriter(F3);
			br =new BufferedReader(F4);
			req1=br1.readLine();
		
			while(req1!=null)
			{
			
			 s2=StringEscapeUtils.escapeXml(req1);
			 bw1.write(s2);
				bw1.newLine();
			req1=br1.readLine();
			}
			
			
			br1.close();
			bw1.close();
			F3.close();
				req=br.readLine();
				System.out.println(req);
				while(req!=null)
				{
				 s1="<String>"+req+"</String>";
				 System.out.println(s1);
				 bw.write(s1);
				bw.newLine();
				req=br.readLine();
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		finally
		{
			try {
				F1.close();
				br.close();

				bw.close();
				
				F2.close();
				F4.close();
				System.out.println("alclose");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
		
	}
	
	}


