import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;

import autoitx4java.AutoItX;

import com.jacob.com.LibraryLoader;

public class RSA {
	
	public static String getPasscode(String file, String pin) throws InterruptedException, UnsupportedFlavorException, IOException {
		checkDLL();
		AutoItX x = new AutoItX();
		x.run(file);
		String h = "[REGEXPTITLE:(.*.RSA SecurID Token)]";
		x.winActivate(h);
		x.winWaitActive(h);
		x.controlSend(h, "", "[CLASS:QWidget; INSTANCE:3]", pin);
		x.controlClick(h, "", "[CLASS:QWidget; INSTANCE:2]");
		Thread.sleep(1000);
		x.controlClick(h, "", "[CLASS:QWidget; INSTANCE:3]");
		String result = x.clipGet();
		
		while (readTemp().equals(result)) {
			
			Thread.sleep(20000);
			x.controlClick(h, "", "[CLASS:QWidget; INSTANCE:3]");
			result = x.clipGet();
		}
		
		writeTemp(result);
		x.winClose(h);
		return result;
	}
	
	private static void checkDLL(){
		String jacobDllVersionToUse;
		if (jvmBitVersion().contains("32")) { jacobDllVersionToUse = "jacob-1.18-M3-x86.dll"; }
		else { jacobDllVersionToUse = "jacob-1.18-M3-x64.dll"; }
		
		File file = new File("lib", jacobDllVersionToUse);
		System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
	}
	
	private static String jvmBitVersion(){
		 return System.getProperty("sun.arch.data.model");
	}

	private static String readTemp() throws IOException {
		String code = "";
		try {
			FileReader reader = new FileReader(System.getProperty("java.io.tmpdir") + "\\rtemp.txt");
	        BufferedReader bufferedReader = new BufferedReader(reader);
	        code = bufferedReader.readLine(); reader.close();
	        if (code == null) { code = ""; }
		} catch(Exception e) {
		}
		return code;
	}

	private static void writeTemp(String code) throws IOException {
		FileWriter writer = new FileWriter(System.getProperty("java.io.tmpdir") + "\\rtemp.txt");
		writer.write(code);
		writer.close();
	}
}
