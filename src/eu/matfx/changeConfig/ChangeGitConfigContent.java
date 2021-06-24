package eu.matfx.changeConfig;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * change git config file to use different user
 * @author m.goerlich
 *
 */
public class ChangeGitConfigContent extends BorderPane
{
	/**
	 * select the config path to .gitconfig
	 */
	private Button buttonDirectory;
	
	/**
	 * shows the selected path to .gitconfig
	 */
	private Label labelDirectory;
	
	/**
	 * Collection of well configured .gitconfig files
	 */
	private ComboBox<String> fileSelectionBox;
	
	/**
	 * change the .gitconfig with the selected file from the combobox
	 */
	private Button change;
	
	/**
	 * show the content of the selected gitconfig file; not editable
	 */
	private TextArea textArea;
	
	
	
	public ChangeGitConfigContent(final Stage primaryStage)
	{
		
		HBox topBox = new HBox(10);
		topBox.setPadding(new Insets(15, 15 , 15, 15));
		
		labelDirectory = new Label(ConfigResourceLocation.getPathToGitConfigFile());
		labelDirectory.setMinWidth(100);
		
		buttonDirectory = new Button("Change Config Path");
		
		buttonDirectory.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event) 
			{
				DirectoryChooser chooser = new DirectoryChooser();
				
				
				if(ConfigResourceLocation.isConfigPathAvailable())
				{
					chooser.setInitialDirectory(new File(ConfigResourceLocation.getPathToGitConfigFile()));
				}
			
				File selectedDirectory = chooser.showDialog(primaryStage);
				
                if(selectedDirectory != null)
                {
                	labelDirectory.setText(selectedDirectory.getAbsolutePath());
                	ConfigResourceLocation.setConfigurePath(Paths.get(selectedDirectory.getAbsolutePath()));
                	
                	//rebuild combobox 
                	List<String> tempList = new ArrayList<String>();
            		if(ConfigResourceLocation.isConfigPathAvailable())
            		{
            			tempList = ConfigResourceLocation.getGitConfigFiles();
            		}
            		ObservableList<String> selectionComboBox = 
            				FXCollections.observableArrayList(tempList);
            		fileSelectionBox.getItems().clear();
                	fileSelectionBox.setItems(selectionComboBox);
            		if(tempList != null && tempList.size() > 0)
            			fileSelectionBox.getSelectionModel().select(0);
                	
                }
			}
			
		});
		
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		
		topBox.getChildren().addAll(labelDirectory, buttonDirectory, separator);
		
		List<String> tempList = new ArrayList<String>();
		if(ConfigResourceLocation.isConfigPathAvailable())
		{
			tempList = ConfigResourceLocation.getGitConfigFiles();
		}
		ObservableList<String> selectionComboBox = 
				FXCollections.observableArrayList(tempList);
		
		
		fileSelectionBox = new ComboBox<String>(selectionComboBox);
		if(tempList != null && tempList.size() > 0)
			fileSelectionBox.getSelectionModel().select(0);
		
		fileSelectionBox.valueProperty().addListener(new ChangeListener<String>() 
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				//change content of textarea
				if(newValue != null)
				{
					String file = fileSelectionBox.getSelectionModel().getSelectedItem();
					textArea.setText(ConfigResourceLocation.getContentFromGitFile(file));
				}
				
			}
			
		});
		
		change = new Button("Use selected config");
		change.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				//change the content of gitconfigure
				String file = fileSelectionBox.getSelectionModel().getSelectedItem();
				if(!file.equals(".gitconfig"))
				{
					//delete old gitConfig and make copy from selected file
					ConfigResourceLocation.changeGitConfigFile(file);
					fileSelectionBox.getSelectionModel().select(0);
				}
				
			}
			
		});
		
		topBox.getChildren().addAll(fileSelectionBox, change);
		this.setTop(topBox);
		
		
		textArea = new TextArea();
		textArea.setEditable(false);
		
		if(tempList != null && tempList.size() > 0)
		{
			//vorbelegung wenn was vorhanden ist
			String file = fileSelectionBox.getSelectionModel().getSelectedItem();
			textArea.setText(ConfigResourceLocation.getContentFromGitFile(file));
		}
	
		this.setCenter(textArea);
	}
	

}
