package eu.matfx.imagedivider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

public class ImageSeperatorContent extends BorderPane
{
	
	private String DEFAULT_IMPORT = "X:/workspaceAndroidMars/MobileV2_Android/res";
	
	private String DEFAULT_EXPORT = "X:/workspaceGluon/BSCMobileV3/BSCMobileV3App/src/main/resources/images";
	
	private String configFile = "config/ImageSeperator.cfg";

	private Properties storedConfiguration;
	
	
	/**
	 * Export folders after conversion
	 * <br>Every folder must corresponding with the SUFFIX array
	 */
	private static String[] FOLDERS = new String[] { "drawable_mdpi", "drawable_hdpi", "drawable_xhdpi", "drawable_xxhdpi", "drawable_xxxhdpi" };

	private TextField importVerzeichnis;
	
	private TextField exportVerzeichnis;
	
	private File importDirectory, exportDirectory;
	
	/**
	 * Suffix from the importing files. With conversion the name of files will be freed from the suffix and copied to the FOLDERS.
	 */
	private static final String[] SUFFIX = new String[]{"_mdpi", "_hdpi",  "_xhdpi",  "_xxhdpi", "_xxxhdpi"};
	
	public ImageSeperatorContent() 
	{
		loadConfig();
		
		
		VBox topBox = new VBox(10);
		topBox.setAlignment(Pos.CENTER_LEFT);
		topBox.setPadding(new Insets(15, 15 , 15, 15));
		
		//Textfield import directory and button
		HBox sourceHBox = new HBox(5);
		
		importVerzeichnis = new TextField();
		importVerzeichnis.setText(DEFAULT_IMPORT);
		
		HBox.setHgrow(importVerzeichnis, Priority.ALWAYS);
		
		Button importDirectorySelection = new Button("Change import directory");
		importDirectorySelection.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				changeImport();
				
			}
			
		});
		sourceHBox.getChildren().addAll(importVerzeichnis, importDirectorySelection);
		
		//texfield export directory and button
		HBox destinationHBox = new HBox(5);
		
		exportVerzeichnis = new TextField();
		exportVerzeichnis.setText(DEFAULT_EXPORT);
		HBox.setHgrow(exportVerzeichnis, Priority.ALWAYS);
		Button exportDirectorySelection = new Button("Change export directory");
		exportDirectorySelection.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				changeExport();
				
			}
			
		});
		destinationHBox.getChildren().addAll(exportVerzeichnis, exportDirectorySelection);
		
		HBox controlHBox = new HBox(5);
		Button startProcess = new Button("Start conversion");
		startProcess.setOnAction(new EventHandler<ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent event) 
			{
				start();
				
			}

			
		});
		
		controlHBox.getChildren().addAll(startProcess);
		
		
		topBox.getChildren().addAll(sourceHBox, destinationHBox, controlHBox);
		this.setTop(topBox);
	}
	

	private void start() {
		if(exportDirectory != null && importDirectory != null)
		{
			
		
			StringBuilder exportPath = new StringBuilder(exportVerzeichnis.getText());
			StringBuilder importPath = new StringBuilder(importVerzeichnis.getText());

			exportPath.append(File.separator);
			importPath.append(File.separator);
		
			//wurschtle alle png Datein im Import durch
			ArrayList<File> files = getPaths(new File(importPath.toString()), new ArrayList<File>()); 
			System.out.println("files " + files.size());
			if(files == null || files.size() < 0)
				return;
			
			//Im Ziel die Unterverzeichnisse anlegen
			
			for(int i = 0; i < FOLDERS.length; i++)
			{
				File dir = new File(exportPath+FOLDERS[i]);
				dir.mkdir();
			}
			
			Iterator<File> iterator = files.iterator();
			while(iterator.hasNext())
			{
				File sourceFile = iterator.next();
				String fileName = sourceFile.getName();
				//Nur png sind von Interesse 
				if(fileName.endsWith(".png"))
				{
					for(int i = 0; i < SUFFIX.length; i++)
					{
						if(fileName.contains(SUFFIX[i]))
						{
							//Dann verändere die Bezeichnung und verschiebe das file in das neue Verzeichnis
							fileName = fileName.replace(SUFFIX[i], "");
							
							//folder array ist immer paarig mit suffix array
							File newFile = new File(exportPath+FOLDERS[i]+File.separator+fileName);
							
							try {
								Files.move(sourceFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							break;
						}
					}
					
				}
				
			}
			
			
		}
		
	}
	
	private static ArrayList<File> getPaths(File file, ArrayList<File> list) {
        if (file == null || list == null || !file.isDirectory())
            return null;
        File[] fileArr = file.listFiles();
        for (File f : fileArr) {
            if (f.isDirectory()) {
                getPaths(f, list);
            }
            list.add(f);
        }
        return list;
    } 
	
	protected void changeExport() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Select the export directory");
		File defaultDirectory = new File(DEFAULT_EXPORT);
		if (defaultDirectory.exists())
		{
			chooser.setInitialDirectory(defaultDirectory);
		}
		exportDirectory = chooser.showDialog(this.getScene().getWindow());
		if (exportDirectory != null)
		{
			exportVerzeichnis.setText(exportDirectory.getAbsolutePath());
		}
		
	}


	private void changeImport()
	{
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Select the import directory");
		File defaultDirectory = new File(DEFAULT_IMPORT);
		if (defaultDirectory.exists())
		{
			chooser.setInitialDirectory(defaultDirectory);
		}
		importDirectory = chooser.showDialog(this.getScene().getWindow());
		if (importDirectory != null)
		{
			importVerzeichnis.setText(importDirectory.getAbsolutePath());
		}
	}
	

	private void loadConfig()
	{
		File file = new File(configFile);
		storedConfiguration = new Properties();
		if (file.exists())
		{
			//read
			try (FileInputStream fis = new FileInputStream(file))
			{
				storedConfiguration.load(fis);

				if(storedConfiguration.getProperty("Import") != null && storedConfiguration.getProperty("Import") != "")
				{
					DEFAULT_IMPORT = storedConfiguration.getProperty("Import");
				}
				
				if(storedConfiguration.getProperty("Export") != null &&  storedConfiguration.getProperty("Export") != "")
				{
					DEFAULT_EXPORT = storedConfiguration.getProperty("Export");
				}
				
				if (storedConfiguration.getProperty("Folders") != null && storedConfiguration.getProperty("Folders") != "")
				{
					//if(storedConfiguration.getProperty("Folders").contains("#"))
					//	FOLDERS = tmpString.split("#");
				}
				//System.out.println("folders " + FOLDERS.length);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		importDirectory = new File(DEFAULT_IMPORT);
		exportDirectory = new File(DEFAULT_EXPORT);
	}
	
	public void saveConfig()
	{
		storedConfiguration.setProperty("Import", importVerzeichnis.getText());
		storedConfiguration.setProperty("Export", exportVerzeichnis.getText());
		
		String tmpFolder = "";
		for (int i = 0; i < FOLDERS.length; i++)
		{
			if (i == 0)
			{
				tmpFolder = FOLDERS[i];
			}
			else
			{
				tmpFolder = tmpFolder + "#" + FOLDERS[i];
			}
		}

		storedConfiguration.setProperty("Folders", tmpFolder);

		try (FileOutputStream fos = new FileOutputStream(configFile))
		{
			storedConfiguration.store(fos, configFile);
			fos.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
