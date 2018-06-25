package com.SupportingFiles;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

public class ConsoleOutput implements ClipboardOwner{
	static String filePath="D:\\OSD_Test_Automation\\MyATT_Web_Selenium\\IO\\Reports\\ConsoleOutput.txt";
		
	@Test
	public static void f() throws IOException {
		BufferedWriter bw=new BufferedWriter(new FileWriter(filePath));
		bw.write(getClipboardContents());
		bw.close();
		ConsoleOutput.openTextFile();
		System.out.println("end");
	}

	public static String getClipboardContents() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText =
				(contents != null) &&
				contents.isDataFlavorSupported(DataFlavor.stringFlavor)
				;
		if (hasTransferableText) {
			try {
				result = (String)contents.getTransferData(DataFlavor.stringFlavor);
			}
			catch (Exception ex){
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void openTextFile(){
		try{
			//check if notepad++ exist on system
			File textFile=new File("C:\\Program Files (x86)\\Notepad++\\notepad++.exe");
			if (textFile.isFile()) {
				Runtime.getRuntime().exec("C:\\Program Files (x86)\\Notepad++\\notepad++.exe "+filePath);
			} else {
				Runtime.getRuntime().exec("notepad "+filePath);
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		//do nothing
	}
}
