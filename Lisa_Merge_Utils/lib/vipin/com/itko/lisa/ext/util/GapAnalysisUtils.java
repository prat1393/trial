
package com.itko.lisa.ext.util;

import java.util.Arrays;
import java.util.LinkedList;

/**  
 * Gap Analysis Framework Main class
 * @author  Ashutosh Satyam (ashutosh.satyam@ca.com)
 * @version 1.0 
 */ 
public class GapAnalysisUtils {

	private static final String[] HELP_TEXT = {
		"This command line tool is used for running gap analysis framework.",
		"Usage : java com.itko.lisa.ext.util.GapAnalysisUtils [arguments]",
		"",
		"Required Arguments :",
		"--properties-file <gap analysis configuration file>" ,
		"	Properties file contains the following information" ,
		"		1. Project target root path " ,
		"		2. LISA Home install directory path " ,
		"		3. spreadsheet containing the gap analysis detail info",
	};


	public static void main(String args[]) throws Throwable {
		LinkedList<String> params = new LinkedList<String>(Arrays.asList(args));
		process(params);
	}

	public static void process(LinkedList<String> params) throws Throwable {
		if (params == null || params.isEmpty() || params.size() < 2) {
			showHelpText();
			return;
		}
		if (!"--properties-file".equals(params.get(0))) {
			showHelpText();
			return;
		}

		String propsFile = params.get(1);
		GapAnalysisExecutor.analyzeAndRenderSIChanges(propsFile);
	}


	private static void showHelpText() {
		for (String line : HELP_TEXT)
			System.out.println(line);
	}

}
