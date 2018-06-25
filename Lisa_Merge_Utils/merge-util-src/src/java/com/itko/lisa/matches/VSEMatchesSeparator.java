package com.itko.lisa.matches;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VSEMatchesSeparator {

	
	private List<String> matchPatternList = new ArrayList<String>();
	private Map<String,String> matchPatternMap = new HashMap<String, String>();
	private Map<String, BufferedWriter>  writerMap = new HashMap<String, BufferedWriter>();
	private String localVSEMatchesFile;
	private boolean isCompileMtachesPatternsDone;
	private String vseFilePath;
	private String patternNotFoundFile;
	private List<Pattern> compiledPatternsList = new ArrayList<Pattern>();	
	public static final String NEW_LINE = "\n";
	public static final String RAF_READ_MODE = "r";
	public static final String RAF_WRITE_MODE = "w";
	public static final String RAF_READ_WRITE_MODE = "rw";
	private int timerInterval;
	private String lisaMatchesReportPath;
	public static void main(String[] args) {
		VSEMatchesSeparator vseVSEMatchesSeparator = new VSEMatchesSeparator();
		/*try {
			String BDS_Merged = "2013-05-07 12:11:37,825 [BDS_Merged [VS_BDS_Merged_Run]";
			String merged = "Merged";
			Pattern patternMerged = Pattern.compile("[VS_"+"\\[A-Z]"+"_"+merged+"_Run]");
			if(patternMerged.matcher(BDS_Merged).find()){
				System.out.println("BDS_Merged");
			}
			String date012813 = "012813";
			Pattern pattern012813 = Pattern.compile("[VS_"+"\\[A-Z]"+"_"+date012813+"_Run]");
			String TGUARD_012813 = "2013-03-20 11:46:39,077 [TGUARD_012813 [VS_TGUARD_012813_Run]";
			if(pattern012813.matcher(TGUARD_012813).find()){
				System.out.println("TGUARD_012813");
			}
			
			if(patternMerged.matcher(TGUARD_012813).find()){
				System.out.println("Oh no 1");
			}
			if(pattern012813.matcher(BDS_Merged).find()){
				System.out.println("Oh no 2");
			}
			
			//vseVSEMatchesSeparator.splitFiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		vseVSEMatchesSeparator.splitFiles();
	}
	private void splitFiles(){
		//load props
		//Read the main vse_matches.log
		//write to multiple vse_matches_XXX.log files
		 long start = System.currentTimeMillis();
		if(!loadProperties()){
			System.out.println("Method splitFiles() loadProperties failed!! ");
			return;
		}
		//make sure vse_matches.log exists

		//all checks done, process the vseMatchesFile
		long totalLines =0;
		try {
			totalLines = procesVSEMatches(getVSEMatchesFile());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> writerIt = writerMap.keySet().iterator();
		while(writerIt.hasNext()){
			closeWriter(writerMap.get(writerIt.next()));
		}
		long end = System.currentTimeMillis();
		long timeTaken = end - start;
		long min = timeTaken / 1000;
		long sec = min % 60;
		min = min / 60;
		System.out.println(" VSEMatchesSeparator Processing Done, Total Lines:"+totalLines +" total time taken " + min
				+ " minute " + sec + " second");
	}
	
	public File getVSEMatchesFile() throws Exception{
		 
		File	vseMatchesFile = new File (vseFilePath+localVSEMatchesFile);
	    	if (!vseMatchesFile.exists()){
	    		System.out.println("Method splitFiles() localVSEMatchesFile doesn't exist!! ");
				throw new FileNotFoundException("Method splitFiles() localVSEMatchesFile doesn't exist!! "+vseFilePath+localVSEMatchesFile) ;
	    	}
		
		return vseMatchesFile;
	}
	public 	BufferedReader getReder(File file) throws Exception{
		return  new BufferedReader(new FileReader(file));
	}

	public void closeReader(Reader reader) {
		if(null != reader)
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	private void closeWriter(Writer writer){
		if(null != writer)
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	public void closeVSEMatches(){
		Iterator<String> writerIt = writerMap.keySet().iterator();
		while(writerIt.hasNext()){
			String key = writerIt.next();
			closeWriter(writerMap.get(key));
			//make the value for this key null
			//next run it will be initialized
			writerMap.put(key, null);
		}
	}
	
	private long procesVSEMatches(File vseMatchesFile) throws Exception{
		long totalLines =0;
		String eachVSEMatchesLine =null;
			BufferedReader br = new BufferedReader(new FileReader(vseMatchesFile));
			while ((eachVSEMatchesLine = br.readLine()) != null)
			{
				totalLines++;
				writeVSEMatches(eachVSEMatchesLine);
			}
			//try finally
			//close stream under loadProp
			closeReader(br);
			Iterator<String> writerIt = writerMap.keySet().iterator();
			while(writerIt.hasNext()){
				closeWriter(writerMap.get(writerIt.next()));
			}
			return totalLines;
	}
	
	public void writeVSEMatches(String eachVSEMatchesLine) throws Exception{
		if(!isCompileMtachesPatternsDone){
			compileMtachesPatterns();
		}
		//now match this line to all the patterns
		//find the matching pattern then write that vse matches log file
		Iterator<Pattern> it = compiledPatternsList.iterator();
		 Pattern pattern = null;
		boolean patternFound = false;
		 while(it.hasNext()){
			pattern = it.next();
			if(pattern.matcher(eachVSEMatchesLine).find() && isValidVSEMatchesLine(eachVSEMatchesLine)){
				//found the pattern for this line
				//write this line to separate vse matches log
				writeIndividiualVSEMatches(matchPatternMap.get(pattern.pattern()),eachVSEMatchesLine);
				patternFound = true;
				break;
			}
		}
		if(!patternFound){
			//extra debugging information from matches file (complete requestresponse )will be written here
			writeIndividiualVSEMatches(patternNotFoundFile,eachVSEMatchesLine);
		}
		 
	}
	private boolean isValidVSEMatchesLine(String eachVSEMatchesLine ){
		boolean isValidVSEMatchesLine = false;
		if(eachVSEMatchesLine.contains("Transaction Navigator No stateless match found") ||
				eachVSEMatchesLine.contains("Transaction Navigator Respond from:") ||
				eachVSEMatchesLine.contains("- Inbound Request")){
			isValidVSEMatchesLine = true;
		}
		return isValidVSEMatchesLine;
	}
	private void writeIndividiualVSEMatches(String individualVSEMatchesFileName, String vseMatchesLine) throws Exception{
		//get a buffered write for this file
		BufferedWriter writer = getWriter(individualVSEMatchesFileName);
		writer.write(vseMatchesLine+NEW_LINE);
	}

	private BufferedWriter getWriter(String individualVSEMatchesFileName) throws Exception{
		BufferedWriter writer = writerMap.get(individualVSEMatchesFileName);
		if(null == writer){
			writer = createBufferedWriter(individualVSEMatchesFileName);
			writerMap.put(individualVSEMatchesFileName, writer);
		}
		return writer;
	}
	
	private BufferedWriter createBufferedWriter(String individualVSEMatchesFileName) throws Exception{
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter(getFile(true,individualVSEMatchesFileName)));
		}
		catch (Exception e)
		{
			System.out.println("Method createBufferedWriter() coudln't create file for:"+vseFilePath+individualVSEMatchesFileName);
			throw e;
		}
		return bw;
	}
	
	private void compileMtachesPatterns(){
		if(isCompileMtachesPatternsDone){
			return;
		}
		
		for(String eachPattern:matchPatternList){
			Pattern pattern = Pattern.compile("[VS_"+"\\[A-Z]"+"_"+eachPattern+"_Run]");
			compiledPatternsList.add(pattern);
			matchPatternMap.put(pattern.pattern(), "vse_matches_"+eachPattern+".log");
			
		}
		isCompileMtachesPatternsDone = true;
	}
	public boolean loadProperties()
	{
		
		Properties prop = new Properties();

		try
		{
			InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("VSEMatchesSeparator.properties");
			if (stream == null){
				System.out.println("Method loadProperties() VSEMatchesSeparator.config couldn't be found");
				return false;
			}
			prop.load(stream);
			String matchPatternProp =prop.getProperty("matchPattern");
			if(null == matchPatternProp || matchPatternProp.trim().length() == 0){
				System.out.println("Method loadProperties() VSEMatchesSeparator.config doesn't have property matchPattern");
				return false;
			}
			localVSEMatchesFile = prop.getProperty("localVSEMatchesFile");
			if(null == localVSEMatchesFile || localVSEMatchesFile.trim().length() == 0){
				System.out.println("Method loadProperties() VSEMatchesSeparator.config doesn't have property localVSEMatchesFile");
				return false;
			}
			vseFilePath = prop.getProperty("vseFilePath");
			if(null == vseFilePath || vseFilePath.trim().length() == 0){
				System.out.println("Method loadProperties() vseFilePath.config doesn't have property localVSEMatchesFile");
				return false;
			}
			patternNotFoundFile = prop.getProperty("patternNotFoundFile");
			if(null == patternNotFoundFile || patternNotFoundFile.trim().length() == 0){
				System.out.println("Method loadProperties() vseFilePath.config doesn't have property patternNotFoundFile");
				return false;
			}
			
			String timerIntervalStr = prop.getProperty("timerInterval");
			if(null == timerIntervalStr || timerIntervalStr.trim().length() == 0){
				System.out.println("Method loadProperties() vseFilePath.config doesn't have property timerInterval");
				return false;
			}
			try {
				timerInterval = Integer.parseInt(timerIntervalStr);
			} catch (Exception e) {
				System.out.println("Method loadProperties() vseFilePath.config  timerInterval not a valid integer");
				return false;
			}
			lisaMatchesReportPath = prop.getProperty("lisaMatchesReportPath");
			if(null == lisaMatchesReportPath || lisaMatchesReportPath.trim().length() == 0){
				System.out.println("Method loadProperties() vseFilePath.config doesn't have property lisaMatchesReportPath");
				return false;
			}
			String [] patterns = matchPatternProp.split(",");
			for(int index =0; index < patterns.length ; index++){
				matchPatternList.add(patterns[index]);
			}
			System.out.println("Method loadProperties() matchPatternList loaded:"+matchPatternList);
			System.out.println("Method loadProperties() All properties\n:"+prop);

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return true;
		
	}
	public List<String> getMatchPatternList() {
		return matchPatternList;
	}
	public void setMatchPatternList(List<String> matchPatternList) {
		this.matchPatternList = matchPatternList;
	}
	public Map<String, String> getMatchPatternMap() {
		return matchPatternMap;
	}
	public void setMatchPatternMap(Map<String, String> matchPatternMap) {
		this.matchPatternMap = matchPatternMap;
	}
	public Map<String, BufferedWriter> getWriterMap() {
		return writerMap;
	}
	public void setWriterMap(Map<String, BufferedWriter> writerMap) {
		this.writerMap = writerMap;
	}
	public String getLocalVSEMatchesFile() {
		return localVSEMatchesFile;
	}
	public void setLocalVSEMatchesFile(String localVSEMatchesFile) {
		this.localVSEMatchesFile = localVSEMatchesFile;
	}
	public boolean isCompileMtachesPatternsDone() {
		return isCompileMtachesPatternsDone;
	}
	public void setCompileMtachesPatternsDone(boolean isCompileMtachesPatternsDone) {
		this.isCompileMtachesPatternsDone = isCompileMtachesPatternsDone;
	}
	public String getVseFilePath() {
		return vseFilePath;
	}
	public void setVseFilePath(String vseFilePath) {
		this.vseFilePath = vseFilePath;
	}
	public String getPatternNotFoundFile() {
		return patternNotFoundFile;
	}
	public void setPatternNotFoundFile(String patternNotFoundFile) {
		this.patternNotFoundFile = patternNotFoundFile;
	}
	public List<Pattern> getCompiledPatternsList() {
		return compiledPatternsList;
	}
	public void setCompiledPatternsList(List<Pattern> compiledPatternsList) {
		this.compiledPatternsList = compiledPatternsList;
	}
	
	public int getTimerInterval() {
		return timerInterval;
	}
	public void setTimerInterval(int timerInterval) {
		this.timerInterval = timerInterval;
	}
	
	public String getLisaMatchesReportPath() {
		return lisaMatchesReportPath;
	}
	public void setLisaMatchesReportPath(String lisaMatchesReportPath) {
		this.lisaMatchesReportPath = lisaMatchesReportPath;
	}
	public File getFile(boolean useBaseVSEFilePath, String fileName) throws Exception{
		File f = null;
		if(useBaseVSEFilePath){
			f = new File(getVseFilePath()+fileName);
		}else{
			f = new File(fileName);
		}
		return f;
	}

	public RandomAccessFile getRandomAccessFile(File f,String mode) throws Exception{
		return new RandomAccessFile(f, mode);
	}
	
	public RandomAccessFile getRandomAccessFile(boolean useBaseVSEFilePath, String fileName,String mode) throws Exception{
		return new RandomAccessFile(getFile(useBaseVSEFilePath,fileName), mode);
	}
	
	
}
