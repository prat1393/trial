package com.att.dbvalidation.services;

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

import com.att.dbvalidation.properties.DataValidationEnum;

@Service
public class AdminService_backup {
	
	@Value("${properties.path}")
	private String PATH;
    private static Properties properties;
	/*static
	{
		if(System.getProperty("os.name").toLowerCase().contains("win"))
		{
			PATH  ="C:\\E2EAutomation\\e2eAutomation\\PropertiesFile\\";
		}else
		{
			PATH  = "/opt/app/stubdomains/component/LMR/sample.properties";
		}
	}*/
	
	public List<String> getReleaseProperties() {
		properties = new Properties();
		List<String> list=new ArrayList<String>();
        try {
        	System.out.println("PATH:::::::::::::::::::::::"+PATH);
        	FileInputStream in = new FileInputStream(PATH+"releasemanagement.properties");        	
            properties.load(in );
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
		
	private static List<String> filterProperties(String discriminator,int pos, String path) {
		properties = new Properties();
		List<String> backends=new ArrayList<>();
        try {
        	 FileInputStream in = new FileInputStream(path);        	
        	 properties.load(in );
        	 Set<String> keys = properties.stringPropertyNames();
        	 for (String key : keys) {
             if(key.contains(discriminator))
             {            	          	 
            	 backends.add(key.split(discriminator)[pos]);          			 
             }
            }
            Collections.sort(backends);
        }
        catch (Exception e) {
        }
        return backends;
	}
	
	public List<String> retrieveApplication(String release) {
		String path = findReleasePath(release);
		return filterProperties("_KEY",0, path);	
	}
	
	
	public List<String> retrieveApplicationBackends(String release, String app) {
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

	private String findReleasePath(String release) {
		properties = new Properties();
		String path=null;
        try {
        	FileInputStream in = new FileInputStream(PATH+"releasemanagement.properties");        	
            properties.load(in );
            path=properties.getProperty(release);       	 	
        } catch (Exception e) {
        }
		return path;
	}
	
	public List<String> showBackendsProperties(String backends, String release) {
		String path = findReleasePath(release);
		List<String> resultlist=new LinkedList<>();
		properties = new Properties();
        try {
        	FileInputStream in = new FileInputStream(path);        	
            properties.load(in );
            resultlist.add(properties.getProperty(backends+"_DRIVER"));
            resultlist.add(properties.getProperty(backends+"_USERNAME"));
            resultlist.add(properties.getProperty(backends+"_PASSWORD"));            
        } catch (Exception e) {
        }
        return resultlist;
	}
	public String retrieveApplicationProperties(String release,String backend, String app ) {
		String path = findReleasePath(release);
		String result=null;
		 try {
	        	FileInputStream in = new FileInputStream(path);        	
	            properties.load(in );
	            
	            result=properties.getProperty(app+"_APPLICATION_"+backend);           
	        } catch (Exception e) {
	        }
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public String createPropertiesFileForApplicationConfig(String newRelease, String presentRelease) {	
		
		properties = new Properties();
		String path= PATH+ newRelease + ".properties";
		String presentReleasePath=PATH+ presentRelease + ".properties";
		FileOutputStream os=null;
		 try {
			 PropertiesConfiguration properties = new PropertiesConfiguration(PATH+"releasemanagement.properties");
			 properties.setProperty(newRelease, path);
			 properties.save(); 
			 
			/* PropertiesConfiguration prop = new PropertiesConfiguration(path);
			 prop.save(); */
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
			String releaseConfigPath = PATH + "releasemanagement.properties";
			try {
				PropertiesConfiguration properties = new PropertiesConfiguration(releaseConfigPath);
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
	
	
	public List<String> showProperties(String release, String backends) {
		List<String> resultlist=new LinkedList<>();
		String rel=release.split("-")[0];
		String drivekey=backends+"_"+rel+"_DRIVER";
		String usernamekey=backends+"_"+rel+"_USERNAME";
		String passwordkey=backends+"_"+rel+"_PASSWORD";
		DataValidationEnum eDriver = Enum.valueOf(DataValidationEnum.class, drivekey);
		DataValidationEnum eUserName = Enum.valueOf(DataValidationEnum.class, usernamekey);
		DataValidationEnum ePassword = Enum.valueOf(DataValidationEnum.class, passwordkey);
		
		resultlist.add(eDriver.getValue());
		resultlist.add(eUserName.getValue());
		resultlist.add(ePassword.getValue());		
		
		return resultlist;
		
		
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
	public List<String> retrieveDBBackends(String release) {
		String path = findReleasePath(release);
		return filterProperties("_DRIVER",0, path);	
	}

	

	

	

	

	

	

	
		
	
	

}
