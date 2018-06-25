package com.att.admin.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Value("${properties.path}")
	private String PATH;
	
	@Value("${properties.file}")
	private String propertyFile;
	
    private Properties properties=null;
    private Properties releaseProperties=null;
    
    private static Long previousTimeStamp=0l;
    private static Long previousTimeStampReleaseFile=0l;
    
    public List<String> getReleaseProperties() throws IOException {
    	init();
		List<String> list=new ArrayList<String>();
        try {
            Set<Object> keys = properties.keySet();
            for (Object k : keys) {           	
                list.add((String)k);
			}
        }
        catch (Exception e) {
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
	}
    
    private void init() throws IOException {   
    	System.out.println("propertyFile: "+propertyFile);
    	File file=new File(propertyFile);
        if (properties == null) {   
        	System.out.println("First Time Upload");
        	previousTimeStamp= file.lastModified();
        	properties = new Properties();        	
        	FileInputStream in=null;
            try {
            	in=new FileInputStream(propertyFile);
                properties.load(in);
    		}
            catch (Exception e) {
            }
            finally {
				in.close();
			}
        }
        else{
        	if (file.lastModified()!=previousTimeStamp) {
        		System.out.println("File modified");
        		FileInputStream in=null;
        		properties = new Properties();
                try {
                	in=new FileInputStream(propertyFile);
					properties.load(in);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
                finally {
    				in.close();
    			}
			}
        }
    }
    
    public List<String> retrieveApplication(String release) {
		String path = null;
		try {
			path = findReleasePath(release);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filterProperties("_KEY",0, path);	
	}	
    
    private String findReleasePath(String release) throws IOException {
    	init();
    	return properties.getProperty(release);
	}
    
    private List<String> filterProperties(String discriminator,int pos, String path) {
    	initReleaseProperty(path);
		List<String> backends=new ArrayList<>();
        Set<String> keys = releaseProperties.stringPropertyNames();
        System.out.println("keys: "+keys);
        for (String key : keys) {
           if(key.contains(discriminator))
             {            	          	 
            	 backends.add(key.split(discriminator)[pos]);          			 
             }
           }
          Collections.sort(backends);
        
        return backends;
	}  
    
    private void initReleaseProperty(String path) {    	
    	File releaseFile=new File(path);
        if (releaseProperties == null) {   
        	System.out.println("First Time Upload Release Related file");
        	releaseProperties = new Properties();
        	previousTimeStampReleaseFile=releaseFile.lastModified();
            try {
            	releaseProperties.load(new FileInputStream(path));
    		}
            catch (Exception e) {
            }
        }
        else{
        	if (releaseFile.lastModified()!=previousTimeStampReleaseFile) {
        		System.out.println("File modified");
				try {
					releaseProperties.load(new FileInputStream(path) );
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
    }  
    
	public List<String> retrieveApplicationBackends(String release, String app) throws IOException {
		String path = findReleasePath(release);
		List<String> list=new ArrayList<>();
		switch (app) {
		case "DB_CONFIGURATION":	
			list=filterProperties("_DRIVER",0, path);	
			break;
		case "DB_VALIDATION_QUERY":			
			break;

		default:
			list=filterProperties(app+"_APPLICATION_",1, path);
			break;
		}
		return list;
	}

	
	
	public List<String> showBackendsProperties(String backends, String release) throws IOException {
		String path = findReleasePath(release);
		List<String> resultlist=new LinkedList<>();
		initReleaseProperty(path);
		resultlist.add(releaseProperties.getProperty(backends+"_DRIVER"));
        resultlist.add(releaseProperties.getProperty(backends+"_USERNAME"));
        resultlist.add(releaseProperties.getProperty(backends+"_PASSWORD"));            
        return resultlist;
	}
	
	public String retrieveApplicationProperties(String release,String backend, String app ) throws IOException {
		String path = findReleasePath(release);
		initReleaseProperty(path);
		return releaseProperties.getProperty(app+"_APPLICATION_"+backend);	
	}
	
	
	@SuppressWarnings("unchecked")
	public String createPropertiesFileForApplicationConfig(String newRelease, String presentRelease) {	
		
		String path= PATH+ newRelease + ".properties";
		String presentReleasePath=PATH+ presentRelease + ".properties";
		FileOutputStream os=null;
		 try {
			 PropertiesConfiguration properties = new PropertiesConfiguration(propertyFile);
			 properties.setProperty(newRelease, path);
			 properties.save(); 			 
			
			 Properties prop = new Properties();
			 os = new FileOutputStream(path);
			 prop.store(os, "Dynamic Property File Creation");				
			 
			 File infile =new File(presentReleasePath);
		 	 File outfile =new File(path);
			 FileUtils.copyFile(infile, outfile);
			 
			 PropertiesConfiguration properties2 = new PropertiesConfiguration(path);
			 Iterator<String> propKey=properties2.getKeys();
			 while (propKey.hasNext()) {
				String string = (String) propKey.next();
				if (string.contains("_PASSWORD")) {
					properties2.setProperty(string, "");					
				}				
			}
			properties2.save(); 
		   
		
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 } catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
		finally {

            try {
                os.close();
                
            } catch (IOException ex) {
            }
		}
		return path;
	}
	
	public void addProperty(String release, String application, String propertyKey, String propertyValue) {
		String path= PATH+ release + ".properties";
		try {
			 PropertiesConfiguration properties = new PropertiesConfiguration(path);
			 properties.setProperty(application+"_APPLICATION_"+propertyKey, propertyValue);
			 properties.save(); 		   
		}
		catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addApplication(String release, String application) {
		String path= PATH+ release + ".properties";
		try {
			 PropertiesConfiguration properties = new PropertiesConfiguration(path);
			 properties.setProperty(application+"_KEY", application);
			 properties.save(); 		   
		}
		catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}		
	}
	
	public String deleteRelease(String release) {
		if (release != null) {
			// Delete the env configuration properties file
			String envConfigPath = PATH + release + ".properties";
			File infile =new File(envConfigPath);
		 	File outfile =new File(PATH + "Archieve\\"+release + ".properties");
			try {
				FileUtils.copyFile(infile, outfile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Files.deleteIfExists(Paths.get(envConfigPath));
				} 
			catch (NoSuchFileException e) {
				System.out.println("No such file/directory exists");
			} catch (DirectoryNotEmptyException e) {
				System.out.println("Directory is not empty.");
			} catch (IOException e) {
				System.out.println("Invalid permissions.");
			}

			//Delete the file details from the release management propertiesfile
			try {
				PropertiesConfiguration properties = new PropertiesConfiguration(propertyFile);
				properties.clearProperty(release);
				properties.save();
				System.out.println(" release details deleted from release management ..... .... " + release);
			} catch (ConfigurationException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Deletion successful.");
			return "Success";
			
		}else{
			return "Fail";
			}

	}
	
	public void updateProperties(String release, String backends, String[] connectionDetails) {
		String rel=release.split("-")[0];
		String drivekey=backends+"_"+rel+"_DRIVER";
		String usernamekey=backends+"_"+rel+"_USERNAME";
		String passwordkey=backends+"_"+rel+"_PASSWORD";
		Map<String, String> propmap=new LinkedHashMap<>();
		
		propmap.put(drivekey, connectionDetails[0]);
		propmap.put(usernamekey, connectionDetails[1]);
		propmap.put(passwordkey, connectionDetails[2]);
			
		updatePropertyFile(propmap);
		
	}
	public void updatePropertyFile(Map<String, String> propmap) {
		try {
			 PropertiesConfiguration properties = new PropertiesConfiguration(PATH);
			 for (Map.Entry<String, String> pair : propmap.entrySet()) {
				 properties.setProperty(pair.getKey(), pair.getValue());
			 }
			 properties.save(); 
		   
		}
		catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public List<String> retrieveDBBackends(String release) throws IOException {
		String path = findReleasePath(release);
		return filterProperties("_DRIVER",0, path);	
	}

	public HashMap<String,String> retrieveEnableApplication(String release) {
		// TODO Auto-generated method stub
		String path = null;
		try {
			path = findReleasePath(release);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String discriminator="STATUS_";
    	initReleaseProperty(path);
    	HashMap<String, String> statusMap=new HashMap<>();
    	Set<Object> keys = releaseProperties.keySet();
    	String key=null;
         for (Object k : keys) {  
        	 key=(String)k;
        	 if(key.contains(discriminator))
        		 statusMap.put(key, releaseProperties.getProperty(key)); 
			}
       return statusMap;
        
	}

	

	

	

	

	

	

	
		
	
	

}
