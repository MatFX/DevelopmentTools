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
	
	private static final String PFAD_ZU_DAT = "/config/";
	
	private static final String DATEI_BEZEZEICHNUNG = "configresource.properties";
	
	private static final String CONFIG_GIT_PATH = "gitResource";
	
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
	 * Zust�ndig f�r das initiale Laden der Datei, noch keine Datei vorhanden dann eine anlegen
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
				file = new File(file.getAbsolutePath() + PFAD_ZU_DAT + DATEI_BEZEZEICHNUNG);
				
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
	
	public static String getPathToGitConfigFile()
	{
		createInstance();
		return instance.configProperties.getProperty(CONFIG_GIT_PATH);
		
	}
	
	/**
	 * pr�ft ob ein Config Pfad vorgegeben worden ist.
	 * <br>Ist beim Start der Software einmalig aufzurufne und l�dt die evtl. vorhandene Einstellung
	 * @return
	 */
	public static boolean isConfigPathAvailable()
	{
		createInstance();
		
		//Anschlie�end pr�fen ob die Datei vorhanden ist
		
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
		FileOutputStream fos = null;
		try 
		{
			File file = new File("");
			//Bevor geschrieben wird pr�fen ob Directory vorhanden ist
			file = new File(file.getAbsoluteFile() + PFAD_ZU_DAT);
			if(!file.exists())
			{
				file.mkdirs();
			}
			
			file = new File("");
			
			
			fos = new FileOutputStream(new File(file.getAbsolutePath() + PFAD_ZU_DAT + DATEI_BEZEZEICHNUNG));
			//Setzen der Eigenschaften und anschlie�en sofort wegspeichern
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
		{
			f.delete();
			System.out.println("lösche f");
		}
		else
			System.out.println("gibt es nicht");
		
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
	

}
