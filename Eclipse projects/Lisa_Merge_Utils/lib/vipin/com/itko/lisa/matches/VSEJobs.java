package com.itko.lisa.matches;

import java.util.Iterator;

public class VSEJobs {

	VSEMatchesSeparator vseMatchesSeparator ;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VSEJobs job = new VSEJobs();
		//1 run vse matches separator
		//2. run lisa matches
		//VSEMatchesSeparator vseMatchesSeparator = new 
		job.vseMatchesSeparator = new VSEMatchesSeparator();
		if(!job.vseMatchesSeparator.loadProperties()){
			System.out.println("Method main() loadProperties failed!! ");
			return;			
		}		
		job.runVSESeparatorJob(args);
		job.runLisaMatchesJob(args);
	}
	
	
	private void runVSESeparatorJob(String[] args){
		long start = System.currentTimeMillis();

		VSEMatchesTimerTask vseMatchesTimerTask = null;
		try {
			//RandomAccessFile r =  vseMatchesSeparator.getRandomAccessFile(true, vseMatchesSeparator.getLocalVSEMatchesFile(), VSEMatchesSeparator.RAF_READ_MODE);
			 //Timer timer = new Timer();
			 vseMatchesTimerTask =  new VSEMatchesTimerTask(vseMatchesSeparator);//,r,timer);
			 //Comment timer stuff, run as regular java class
			 //timer.scheduleAtFixedRate(vseMatchesTimerTask,0,vseMatchesSeparator.getTimerInterval()*1000);
			 vseMatchesTimerTask.splitMatches();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		long timeTaken = end - start;
		long min = timeTaken / 1000;
		long sec = min % 60;
		min = min / 60;
		System.out.println(" runVSESeparatorJob  method Done, Total Lines:"+vseMatchesTimerTask.getTotalLines() +" total time taken " + min
				+ " minute " + sec + " second");		

	}
	private void runLisaMatchesJob(String[] args){
		
		try {
		//get the number of matches files generated
		Iterator<String> matchesFilesIt = vseMatchesSeparator.getMatchPatternMap().keySet().iterator();
		String matchesFile = null;
		
		while(matchesFilesIt.hasNext()){
			matchesFile = vseMatchesSeparator.getMatchPatternMap().get(matchesFilesIt.next());
			CreateLisaIdReportTask task =new CreateLisaIdReportTask( new CreateLisaIdReport(),getVSEMatchesFile(matchesFile),getHTMLReportFileName(matchesFile));
			new Thread(task).start();
			//task.run();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private String getVSEMatchesFile(String matchesFile){
		return vseMatchesSeparator.getVseFilePath() + matchesFile;
	}
	
	private String getHTMLReportFileName(String matchesFile){
		String reportBasePath = vseMatchesSeparator.getLisaMatchesReportPath();
		int index = matchesFile.indexOf(".");
		String htmlReportFile = reportBasePath+ matchesFile.substring(0, index)+"_report.html";
		return htmlReportFile;
	}
	
}
