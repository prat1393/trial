package com.itko.lisa.matches;

import java.io.BufferedReader;
import java.io.RandomAccessFile;
import java.util.Timer;
import java.util.TimerTask;

public class VSEMatchesTimerTask extends TimerTask {
	private VSEMatchesSeparator vseMatchesSeparator;
	private RandomAccessFile  raf;
	private Timer timer;
	private long totalLines =0;
	public VSEMatchesTimerTask(VSEMatchesSeparator vseMatchesSeparator,RandomAccessFile  raf, Timer timer){
		this.raf = raf;
		this.vseMatchesSeparator = vseMatchesSeparator;
		this.timer = timer;
	}
	public VSEMatchesTimerTask(VSEMatchesSeparator vseMatchesSeparator){
		this.vseMatchesSeparator = vseMatchesSeparator;
	}

	public long getTotalLines() {
		return totalLines;
	}


	public void setTotalLines(long totalLines) {
		this.totalLines = totalLines;
	}


	@Override
	public void run() {
		long start = System.currentTimeMillis();
		splitMatches();
		long end = System.currentTimeMillis();
		long timeTaken = end - start;
		long min = timeTaken / 1000;
		long sec = min % 60;
		min = min / 60;
		System.out.println(" VSEMatchesTimerTask run() method Done, Total Lines:"+totalLines +" total time taken " + min
				+ " minute " + sec + " second");	
	}
	private void cehckForPropFileChange(){
		//based on the prop file changes 
		//cancel or change the task intervals
		//or get new files??
	}

	public void splitMatches(){
		//Can check the latest properties to adjust the timer interval
		try {

			totalLines =0;
			String eachVSEMatchesLine =null;
			BufferedReader br = vseMatchesSeparator.getReder(vseMatchesSeparator.getVSEMatchesFile());
			while ((eachVSEMatchesLine = br.readLine()) != null)
			{
				totalLines++;
				vseMatchesSeparator.writeVSEMatches(eachVSEMatchesLine);
			}
			vseMatchesSeparator.closeVSEMatches();
			vseMatchesSeparator.closeReader(br);

		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
}
