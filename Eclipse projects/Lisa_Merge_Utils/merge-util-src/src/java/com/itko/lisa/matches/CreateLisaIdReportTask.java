package com.itko.lisa.matches;



public class CreateLisaIdReportTask implements Runnable {
	private String vseMatchesFileName;
	private CreateLisaIdReport report;
	private String reportHtml;
	public CreateLisaIdReportTask(CreateLisaIdReport report, String vseMatchesFileName, String reportHtml){
		this.report = report;
		this.vseMatchesFileName = vseMatchesFileName;
		this.reportHtml = reportHtml;
	}
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		report.processFiles(reportHtml,vseMatchesFileName);
		long end = System.currentTimeMillis();
		long timeTaken = end - start;
		long min = timeTaken / 1000;
		long sec = min % 60;
		min = min / 60;
		System.out.println(" CreateLisaIdReportTask run() method Done, vseMatchesFileName:"+vseMatchesFileName +  " reportHtml:  "+reportHtml +" total time taken " + min
				+ " minute " + sec + " second");		
	}

}
