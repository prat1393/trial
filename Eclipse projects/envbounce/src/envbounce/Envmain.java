package envbounce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;



import com.jcraft.jsch.*;
public class Envmain {

	public static void main(String[] args) throws IOException, JSchException {
		// TODO Auto-generated method stub
		JSch jsch = new JSch();

		String command = "ls";
		Session session = jsch.getSession("ps439r", "130.9.203.229", 22);
		session.setPassword("89959671");
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		
		session.connect();

		ChannelExec channelExec = (ChannelExec)session.openChannel("exec");

        InputStream in = channelExec.getInputStream();

  
        channelExec.setCommand("sudo su - t1eam4c18");
        channelExec.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        int index = 0;

        while ((line = reader.readLine()) != null)
        {
            System.out.println(++index + " : " + line);
        }
        
        channelExec.disconnect();
     
        
        ChannelExec channelExec1 = (ChannelExec)session.openChannel("exec");

        InputStream in1 = channelExec1.getInputStream();

  
        channelExec1.setCommand("ls");
        channelExec1.connect();

        BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1));
        String line1;
        int index1 = 0;

        while ((line1 = reader1.readLine()) != null)
        {
            System.out.println(++index1 + " : " + line1);
        }
        
        channelExec1.disconnect();
        session.disconnect();

        System.out.println("Done!");
    }
    
}
