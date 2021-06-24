package eu.matfx.changeConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigResourceLocation 
{
	private static ConfigResourceLocation instance = null;
	
	private Properties configProperties = null;
	
	private static final String PATH_TO_FILE = "/config/";
	
	private static final String FILE_NAME = "configresource.properties";
	
	private static final String CONFIG_GIT_PATH = "gitResource";
	
	private static final String TOTAL_WIDTH = "total_w";
	
	private static final String TOTAL_HEIGHT = "total_h";
	
	private static void createInstance() 
	{
		if(instance == null)
			instance = new ConfigResourceLocation();
	}
	
	private ConfigResourceLocation()
	{
		loadProperty();
/*
		if(configProperties != null)
			globalePfad = configProperties.getProperty(CONFIG_GIT_PATH);
		
		if(globalePfad == null)
			globalePfad = ".";
		*/
	}
	
	/** 
	 * load file
	 */
	private void loadProperty() 
	{
		if(configProperties == null)
		{
			File file = null;
			Reader reader = null;
			try 
		    {
				configProperties = new Properties();
				file = new File("");
				file = new File(file.getAbsolutePath() + PATH_TO_FILE + FILE_NAME);
				
				if(file.exists())
				{

					reader = new FileReader(file);
					configProperties.load(reader);
				}
				
			}
		    catch ( Exception e ) {e.printStackTrace();}
			finally
			{ 
				if(reader != null)
					try {reader.close();} catch (IOException e) {}
			}
		}
	}
	
	/**
	 * get the git path
	 * @return
	 */
	public static String getPathToGitConfigFile()
	{
		createInstance();
		return instance.configProperties.getProperty(CONFIG_GIT_PATH);
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean isConfigPathAvailable()
	{
		createInstance();
		
		String value = instance.configProperties.getProperty(CONFIG_GIT_PATH);
		if(value != null && value.length() > 0)
		{
			return true;
		}
		else
			return false;
	}

	public static void setConfigurePath(Path configurePath)
	{
		try 
		{
			FileOutputStream fos = getFileOutputStream();
			//set the new content of the property and write file
			instance.configProperties.setProperty(CONFIG_GIT_PATH, ""+configurePath.toString());
			instance.configProperties.store(fos, "");
			fos.flush();
			fos.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	private static FileOutputStream getFileOutputStream() throws FileNotFoundException 
	{
		File file = new File("");
		file = new File(file.getAbsoluteFile() + PATH_TO_FILE);
		if(!file.exists())
		{
			file.mkdirs();
		}
		
		file = new File("");
		
		
		return new FileOutputStream(new File(file.getAbsolutePath() + PATH_TO_FILE + FILE_NAME));
	}

	public static List<String> getGitConfigFiles() 
	{
		List<String> rueckgabe = new ArrayList<String>();
		if(ConfigResourceLocation.isConfigPathAvailable())
		{
			File f  = new File(ConfigResourceLocation.getPathToGitConfigFile());
			File[] files = f.listFiles();
			
			for(int i = 0; i < files.length; i++)
			{
				File tmpFile = files[i];
				if(tmpFile.getName().endsWith(".gitconfig"))
				{
					rueckgabe.add(tmpFile.getName());
				}
				
			}
			
		}
		return rueckgabe;
	}

	public static String getContentFromGitFile(String file) 
	{
		createInstance();
		StringBuilder sb = new StringBuilder("");
		
		File f  = new File(ConfigResourceLocation.getPathToGitConfigFile()+File.separator+file);
		System.out.println("f " + f);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			while((line = br.readLine()) != null)
			{
				sb.append(line);
				sb.append("\n");
			}
			br.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return sb.toString();
	}

	/**
	 * delete the old config file and make new config file (param).
	 * @param file
	 */
	public static void changeGitConfigFile(String file) 
	{
		File f  = new File(ConfigResourceLocation.getPathToGitConfigFile()+File.separator+".gitconfig");
		if(f.exists())
			f.delete();
		
		File newFile = new File(ConfigResourceLocation.getPathToGitConfigFile()+File.separator+".gitconfig");
		
		
		File soruce  = new File(ConfigResourceLocation.getPathToGitConfigFile()+File.separator+file);
		
		try 
		{
			Files.copy(soruce.toPath(), newFile.toPath());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getTotal_Width()
	{
		createInstance();
		return instance.configProperties.getProperty(TOTAL_WIDTH, "150");
	}
	
	public static void setTotal_Width(String width)
	{
		try 
		{
			FileOutputStream fos = getFileOutputStream();
			//set the new content of the property and write file
			instance.configProperties.setProperty(TOTAL_WIDTH, width);
			instance.configProperties.store(fos, "");
			fos.flush();
			fos.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	public static String getTotal_Height()
	{
		createInstance();
		return instance.configProperties.getProperty(TOTAL_HEIGHT, "60");
	}
	
	public static void setTotal_Height(String height)
	{
		
		try 
		{
			FileOutputStream fos = getFileOutputStream();
			//set the new content of the property and write file
			instance.configProperties.setProperty(TOTAL_HEIGHT, height);
			instance.configProperties.store(fos, "");
			fos.flush();
			fos.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	

}
